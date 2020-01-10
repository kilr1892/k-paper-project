package cn.edu.zju.kpaperproject.service.impl;


import cn.edu.zju.kpaperproject.dto.EngineFactoryManufacturingTask;
import cn.edu.zju.kpaperproject.dto.TransactionContract;
import cn.edu.zju.kpaperproject.enums.CalculationEnum;
import cn.edu.zju.kpaperproject.enums.EngineFactoryEnum;
import cn.edu.zju.kpaperproject.enums.NumberEnum;
import cn.edu.zju.kpaperproject.enums.SupplierEnum;
import cn.edu.zju.kpaperproject.mapper.*;
import cn.edu.zju.kpaperproject.pojo.*;
import cn.edu.zju.kpaperproject.service.BeforeNextTask;
import cn.edu.zju.kpaperproject.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class BeforeNextTaskImpl implements BeforeNextTask {

    @Autowired
    private TbEngineFactoryMapper tbEngineFactoryMapper;
    @Autowired
    private TbEngineFactoryDynamicMapper tbEngineFactoryDynamicMapper;
    @Autowired
    private TbSupplierMapper tbSupplierMapper;
    @Autowired
    private TbSupplierDynamicMapper tbSupplierDynamicMapper;

    @Autowired
    private TbRelationMatrixMapper tbRelationMatrixMapper;

    @Autowired
    private EngineFactoryFinalProvisionMapper engineFactoryFinalProvisionMapper;

    @Autowired
    private TbSupplierTypeAvgMapper tbSupplierTypeAvgMapper;

    @Autowired
    private TbBalanceMapper tbBalanceMapper;

    @Autowired
    private TbTransactionContractMapper tbTransactionContractMapper;

    @Autowired
    private DemandForecastMapper demandForecastMapper;
    /**
     * 计算总资产
     * // 计算主机厂的产能利用率
     * // 主机厂修改服务质量-售价-产能
     * // 计算供应商的产能利用率
     * // 供应商修改服务质量-售价-产能
     * // 企业进入与退出
     * // 双方信誉度归一化
     * <p>
     * 对主机厂动态数据及服务商动态数据重算
     *
     * @param experimentsNumber                          实验次数
     * @param cycleTime                                  循环次数
     * @param listEngineFactoryFinalProvisions           最终交货结果集合
     * @param listOrderPlus                              实际交易结果集合
     * @param listTransactionContract                    交易契约集合
     * @param listEngineFactory                          主机厂集合
     * @param listEngineFactoryDynamic                   主机厂动态数据的集合
     * @param listSupplier                               供应商集合
     * @param listSupplierDynamics                       所有存活服务商动态数据集合
     * @param mapRelationshipMatrix2WithTbRelationMatrix 关系矩阵
     */
    @Override
    public void beforeNextTask(
            int experimentsNumber
            , int cycleTime
            , List<EngineFactoryFinalProvision> listEngineFactoryFinalProvisions
            , List<OrderPlus> listOrderPlus
            , ArrayList<TransactionContract> listTransactionContract
            , List<TbEngineFactory> listEngineFactory
            , List<TbEngineFactoryDynamic> listEngineFactoryDynamic
            , List<TbSupplier> listSupplier
            , List<TbSupplierDynamic> listSupplierDynamics
            , Map<String, TbRelationMatrix> mapRelationshipMatrix2WithTbRelationMatrix
            , ArrayList<ArrayList<EngineFactoryManufacturingTask>> listListEngineFactoryTaskDecomposition) {

        // 填充map数据
        Map<String, TbEngineFactory> mapEngineFactory = getMapEngineFactoryIdVsEngineFactory(listEngineFactory);
        Map<String, TbSupplier> mapSupplier = getMapSupplierIdVsSupplier(listSupplier);

        Map<String, TbEngineFactoryDynamic> mapEngineFactoryDynamic = getMapEngineFactoryIdVsEngineFactoryDynamic(listEngineFactoryDynamic);
        Map<String, TbSupplierDynamic> mapSupplierDynamic = getMapSupplierIdVsSupplierDynamic(listSupplierDynamics);

        // 暂存主机厂利润和
        Map<String, Integer> mapEngineFactoryProfitSum = new HashMap<>(100);

        // 暂存供应方利润和
        Map<String, Integer> mapSupplierProfitSum = new HashMap<>(800);

        // 用来存id, 看是否有交易
        HashMap<String, OrderPlus> mapEngineIdVsOrderPlus = new HashMap<>(100);
        // 供应商对应所有的订单映射
        HashMap<String, List<TransactionContract>> mapSupplierIdVsTransactionContract = new HashMap<>(100);

        for (TransactionContract aTransactionContract : listTransactionContract) {
            String supplierId = aTransactionContract.getSupplierId();
            if (!mapSupplierIdVsTransactionContract.containsKey(supplierId)) {
                // 无
                List<TransactionContract> listTmp = new ArrayList<>();
                listTmp.add(aTransactionContract);
                mapSupplierIdVsTransactionContract.put(supplierId, listTmp);
            } else {
                // 有
                List<TransactionContract> listTmp = mapSupplierIdVsTransactionContract.get(supplierId);
                listTmp.add(aTransactionContract);
            }

        }


        // 算出各主机厂与供应商之间的交易利润和,并加入到各自对应的map集合中
        setMapEngineFactoryAndSupplierProfitSum(listOrderPlus, mapEngineFactoryProfitSum, mapSupplierProfitSum, mapEngineIdVsOrderPlus, mapEngineFactoryDynamic, mapSupplierDynamic);


        // 主机厂总资产计算
        // 主机厂与市场交易Map
        HashMap<String, EngineFactoryFinalProvision> mapEngineIdVsEngineFactoryFinalProvision = new HashMap<>(100);
        // 用来存成交价格
        int sumFinalMarketPrice = 0;
        // 用来记录产品的成交质量
        int sumFinalMarketQuality = 0;
        for (EngineFactoryFinalProvision aEngineFactoryFinalProvision : listEngineFactoryFinalProvisions) {
            String engineFactoryId = aEngineFactoryFinalProvision.getEngineFactoryId();
            mapEngineIdVsEngineFactoryFinalProvision.put(engineFactoryId, aEngineFactoryFinalProvision);

            // 算一下成交价格的总和
            sumFinalMarketPrice += aEngineFactoryFinalProvision.getFinalMarketPrice();
            // 算一下产品的成交质量总和
            sumFinalMarketQuality += aEngineFactoryFinalProvision.getFinalMarketQuality();
        }
        // 产品成交价的平均值
        double avgFinalMarketPrice = sumFinalMarketPrice * 1D / listEngineFactoryFinalProvisions.size();
        // 产品成交的平均质量
        double avgFinalMarketQuality = sumFinalMarketQuality * 1D / listEngineFactoryFinalProvisions.size();

        // 计算所有主机厂的总资产
        calAndSetEngineFactoryTotalAssets(listEngineFactoryDynamic, mapEngineFactoryProfitSum, mapEngineIdVsOrderPlus, mapEngineIdVsEngineFactoryFinalProvision);

        // 计算供应商总资产并更新
        calAndSetSupplierTotalAssets(listSupplierDynamics, mapSupplierProfitSum);

        // 对主机厂计算并设置产能利用率, 调整产能/价格/质量
        calAndSetEngineFactoryCapacityUtilizationAndAdjustCapacityPriceQuality(listEngineFactoryDynamic, mapEngineIdVsEngineFactoryFinalProvision, avgFinalMarketPrice, avgFinalMarketQuality);
        // # 计算所有供应商的产能利用率
        // 一类服务市场总需求数量
        int[] sumArrSupplierOrderNumber = new int[5];
        // 一类服务供应商的个数
        int[] sumArrSupplierNumber = new int[5];
        // 一类供应商需求数量平均值
        double[] avgArrSupplierOrderNumber = new double[5];

        // 产品质量
        int[] sumArrSupplierQuality = new int[5];
        // 产品成交的平均质量
        int[] avgArrSupplierQuality = new int[5];

        // 计算供应商一类服务的市场需求量/服务商个数/平均需求/质量/平均质量
        calArrAvgNumberAndQuantity(listTransactionContract, sumArrSupplierOrderNumber, sumArrSupplierNumber, avgArrSupplierOrderNumber, sumArrSupplierQuality, avgArrSupplierQuality);


        // 计算供应商的产能利用率
        calAndSetSupplierCapacityUtilizationAndAdjustCapacityPriceQuality(listSupplierDynamics, avgFinalMarketPrice, avgArrSupplierQuality, mapSupplierIdVsTransactionContract, listListEngineFactoryTaskDecomposition);

        // # 企业进入与退出
        // 主机厂信誉度最高的
        TbEngineFactoryDynamic engineFactoryDynamicWithHighestCredit = null;
        // 主机厂信誉度
        double sumEngineFactoryCreditWithAlive = 0;
        int engineFactoryIsAliveNumber = 0;
        for (TbEngineFactoryDynamic tmp : listEngineFactoryDynamic) {
            String engineFactoryId = tmp.getEngineFactoryId();
            TbEngineFactory tbEngineFactory = mapEngineFactory.get(engineFactoryId);
            if (tbEngineFactory.getEngineFactoryAlive()) {
                engineFactoryIsAliveNumber++;
                // 存活才算
                if (engineFactoryIsAliveNumber == 1) {
                    // 第一个要初始化
                    engineFactoryDynamicWithHighestCredit = tmp;
                    sumEngineFactoryCreditWithAlive += tmp.getEngineFactoryCreditH();
                } else {
                    double engineFactoryCredit = tmp.getEngineFactoryCreditH();
                    sumEngineFactoryCreditWithAlive += engineFactoryCredit;
                    if (engineFactoryCredit > engineFactoryDynamicWithHighestCredit.getEngineFactoryCreditH()) {
                        engineFactoryDynamicWithHighestCredit = tmp;
                    } else if (engineFactoryCredit == engineFactoryDynamicWithHighestCredit.getEngineFactoryCreditH()) {
                        // 信誉度相同, 要出价高的
                        if (tmp.getEngineFactoryPricePU() > engineFactoryDynamicWithHighestCredit.getEngineFactoryPricePU()) {
                            engineFactoryDynamicWithHighestCredit = tmp;
                        }
                    }
                }
            }
        }
        // 所有存活主机厂平均信誉度
        double aveEngineFactoryCredit = 0d;
        if (engineFactoryIsAliveNumber != 0) {
            aveEngineFactoryCredit = sumEngineFactoryCreditWithAlive / engineFactoryIsAliveNumber;
        } else {
            aveEngineFactoryCredit = 1;
        }

        // 求出供应商信誉的最高的
        // 供应商信誉度
        TbSupplierDynamic supplierDynamicWithHighestCredit = null;
        double sumSupplierCreditWithAlive = 0;
        int supplierIsAliveNumber = 0;
        for (TbSupplierDynamic tmp : listSupplierDynamics) {
            String supplierId = tmp.getSupplierId();
            TbSupplier tbSupplier = mapSupplier.get(supplierId);
            if (tbSupplier.getSupplierAlive()) {
                // 存活才算
                supplierIsAliveNumber++;
                if (supplierIsAliveNumber == 1) {
                    supplierDynamicWithHighestCredit = tmp;
                    sumSupplierCreditWithAlive += tmp.getSupplierCreditA();
                } else {
                    double supplierCredit = tmp.getSupplierCreditA();
                    sumSupplierCreditWithAlive += supplierCredit;
                    if (supplierCredit > supplierDynamicWithHighestCredit.getSupplierCreditA()) {
                        supplierDynamicWithHighestCredit = tmp;
                    } else if (supplierCredit == supplierDynamicWithHighestCredit.getSupplierCreditA()) {
                        // 信誉度相同, 要出价高的
                        if (tmp.getSupplierPricePU() > supplierDynamicWithHighestCredit.getSupplierPricePU()) {
                            supplierDynamicWithHighestCredit = tmp;
                        }
                    }
                }
            }
        }
        // 所有存活供应商平均信誉度
        double aveSupplierCredit = sumSupplierCreditWithAlive / supplierIsAliveNumber;

        // 主机厂的退出
        TbEngineFactory tbEngineFactory = null;
        for (TbEngineFactoryDynamic aEngineFactoryDynamic : listEngineFactoryDynamic) {
            int engineFactoryTotalAssets = aEngineFactoryDynamic.getEngineFactoryTotalAssetsP();
            if (engineFactoryTotalAssets < 0) {
                String engineFactoryId = aEngineFactoryDynamic.getEngineFactoryId();
                tbEngineFactory = mapEngineFactory.get(engineFactoryId);
                tbEngineFactory.setEngineFactoryAlive(false);
            }
        }
        // 供应商的退出
        TbSupplier tbSupplier = null;
        for (TbSupplierDynamic aSupplierDynamic : listSupplierDynamics) {
            int supplierTotalAssets = aSupplierDynamic.getSupplierTotalAssetsP();
            if (supplierTotalAssets < 0) {
                String supplierId = aSupplierDynamic.getSupplierId();
                tbSupplier = mapSupplier.get(supplierId);
                tbSupplier.setSupplierAlive(false);
            }
        }

        // 淘汰
        eliminationEngineFactoryAndSupplier(listEngineFactoryDynamic, mapEngineFactory, listSupplierDynamics, mapSupplier);

        // 主机厂创新
        for (TbEngineFactoryDynamic aEngineFactoryDynamic : listEngineFactoryDynamic) {
            String engineFactoryId = aEngineFactoryDynamic.getEngineFactoryId();
            if (mapEngineFactory.get(engineFactoryId).getEngineFactoryAlive()) {
                double engineFactoryInnovationProbability = aEngineFactoryDynamic.getEngineFactoryInnovationProbability();
                if (RandomUtils.nextDouble(0, 1) < engineFactoryInnovationProbability) {
                    // 有创新
                    aEngineFactoryDynamic.setEngineFactoryWhetherInnovation(true);
                    aEngineFactoryDynamic.setEngineFactoryInnovationTimes(aEngineFactoryDynamic.getEngineFactoryInnovationTimes() + 1);
                    // 质量改变
                    int newEngineFactoryQuality = aEngineFactoryDynamic.getEngineFactoryQualityQ() + RandomUtils.nextInt(0, 5) - 1;
                    // 质量限
                    newEngineFactoryQuality = newEngineFactoryQuality > 10 ? 10 : (newEngineFactoryQuality < 1 ? 1 : newEngineFactoryQuality);
                    aEngineFactoryDynamic.setEngineFactoryQualityQ(newEngineFactoryQuality);
                } else {
                    // 无创新
                    aEngineFactoryDynamic.setEngineFactoryWhetherInnovation(false);
                }
            }
        }
        // 供应商创新
        for (TbSupplierDynamic aSupplierDynamic : listSupplierDynamics) {
            String supplierId = aSupplierDynamic.getSupplierId();
            if (mapSupplier.get(supplierId).getSupplierAlive()) {
                double supplierInnovationProbability = aSupplierDynamic.getSupplierInnovationProbability();
                if (RandomUtils.nextDouble(0, 1) < supplierInnovationProbability) {
                    // 有创新
                    aSupplierDynamic.setSupplierWhetherInnovation(true);
                    aSupplierDynamic.setSupplierInnovationTimes(aSupplierDynamic.getSupplierInnovationTimes() + 1);
                    // 质量改变
                    int newSupplierQuality = aSupplierDynamic.getSupplierQualityQs() + RandomUtils.nextInt(0, 5) - 1;
                    // 质量限
                    newSupplierQuality = newSupplierQuality > 10 ? 10 : (newSupplierQuality < 1 ? 1 : newSupplierQuality);
                    aSupplierDynamic.setSupplierQualityQs(newSupplierQuality);
                } else {
                    // 无创新
                    aSupplierDynamic.setSupplierWhetherInnovation(false);
                }
            }
        }

        // 信誉度前30的主机厂集合
        TbEngineFactoryDynamic[] arrEngineFactoryWith30HighestCredit = genEngineFactoryWith30HighestCredit(listEngineFactory, mapEngineFactoryDynamic);
        // 信誉度前30的供应商集合
        TbSupplierDynamic[] arrSupplierDynamicWith30HighestCredit = genSupplierWith30HighestCredit(listSupplier, mapSupplierDynamic);

        // 主机厂的进入
        // 所有还存活主机厂实际供给数
        int sumFinalProductNumberWithAlive = 0;
        // 算一下所有的主机厂实际供给数
        for (EngineFactoryFinalProvision aEngineFactoryFinalProvision : listEngineFactoryFinalProvisions) {
            String engineFactoryId = aEngineFactoryFinalProvision.getEngineFactoryId();
            TbEngineFactory tmp = mapEngineFactory.get(engineFactoryId);
            if (tmp.getEngineFactoryAlive()) {
                // 存活才加实际供给数
                sumFinalProductNumberWithAlive += aEngineFactoryFinalProvision.getFinalProductNumber();
            }
        }



        // 用来存储已有存活的主机厂位置
        Map<Double, Double> mapEngineFactoryPosition = new HashMap<>();
        // 用来存储已有存活的供应商位置
        Map<Double, Double> mapSupplierPosition = new HashMap<>();
        genMapEngineFactoryPositionAmdMapSupplierPosition(mapEngineFactoryPosition, listEngineFactory
                , mapSupplierPosition, listSupplier);

        // 用来存新生成的主机厂id, 和信誉度最高的供应商id        supplierDynamicWithHighestCredit.getSupplierId();
        Map<String, String> mapNewEngineFactoryIdVsSupplierIdWithHighestCredit = new HashMap<>(3);
        EngineFactoryFinalProvision engineFactoryFinalProvision = listEngineFactoryFinalProvisions.get(0);
        int marketNeedNumber = engineFactoryFinalProvision.getMarketNeedNumber();
        TbEngineFactoryDynamic tbEngineFactoryDynamic;

        // 需求预测的初始值
        int initMarketNeedNumber = demandForecastMapper.selectByPrimaryKey(0).getTrueDemandForecast();

        if (marketNeedNumber > sumFinalProductNumberWithAlive) {
            // 真实需求 > 所有主机厂的(阶段结束, 实际能提供的产品)之和
            // 随机生成1~3个主机厂
            int tmp = RandomUtils.nextInt(3, 7);
            for (int i = 0; i < tmp; i++) {
                tbEngineFactory = new TbEngineFactory();
                tbEngineFactoryDynamic = new TbEngineFactoryDynamic();
                // 第几轮实验
                tbEngineFactory.setExperimentsNumber(experimentsNumber);
                // 循环
                tbEngineFactory.setCycleTimes(cycleTime);

                // 工厂id
                String engineFactoryId = CommonUtils.genId();
//                log.info("new engine factory id  " + engineFactoryId);
//                log.info("  ");
//                log.info("  ");
//                log.info("  ");
                tbEngineFactory.setEngineFactoryId(engineFactoryId);
                // 随机获取信誉度较大的那个供应商动态数据
                supplierDynamicWithHighestCredit = arrSupplierDynamicWith30HighestCredit[RandomUtils.nextInt(0, arrSupplierDynamicWith30HighestCredit.length)];


                mapNewEngineFactoryIdVsSupplierIdWithHighestCredit.put(engineFactoryId, supplierDynamicWithHighestCredit.getSupplierId());

                // 地理位置
                tbSupplier = mapSupplier.get(supplierDynamicWithHighestCredit.getSupplierId());

//                double[] position = genNewPosition(new double[]{tbSupplier.getSupplierLocationGX(), tbSupplier.getSupplierLocationGY()}, mapEngineFactoryPosition);
                double[] position;
                if (i < tmp * 0.7) {
                    // 位置70%按照原来的
                    position = genNewPosition(new double[]{tbSupplier.getSupplierLocationGX(), tbSupplier.getSupplierLocationGY()}, mapEngineFactoryPosition);
                } else {
                    // 30%随机
                    position = InitEngineFactoryUtils.initPosition(mapEngineFactoryPosition);
                }

                tbEngineFactory.setEngineFactoryLocationGX(position[NumberEnum.POSITION_X_ARRAY_INDEX]);
                tbEngineFactory.setEngineFactoryLocationGY(position[NumberEnum.POSITION_Y_ARRAY_INDEX]);
                // 存活
                tbEngineFactory.setEngineFactoryAlive(true);
                // 存活次数初始化
                tbEngineFactory.setEngineFactoryAliveTimes(0);

                // 工厂动态数据------------------
                tbEngineFactoryDynamic.setCycleTimes(cycleTime);
                tbEngineFactoryDynamic.setEngineFactoryId(engineFactoryId);
                // 初始总资产
                tbEngineFactoryDynamic.setEngineFactoryTotalAssetsP(InitEngineFactoryUtils.initTotalAssets());
                // 信誉度
                // 主机厂信誉度平均值
                tbEngineFactoryDynamic.setEngineFactoryCreditH(aveEngineFactoryCredit);
                // 最大产能
                tbEngineFactoryDynamic.setEngineFactoryCapacityM(InitEngineFactoryUtils.initCapacity());
                // 价格
                int[] price = InitEngineFactoryUtils.initPrice();
                tbEngineFactoryDynamic.setEngineFactoryPricePL(price[NumberEnum.PRICE_LOW_ARRAY_INDEX]);
                tbEngineFactoryDynamic.setEngineFactoryPricePU(price[NumberEnum.PRICE_UPPER_ARRAY_INDEX]);
                // 质量
                int quality = InitEngineFactoryUtils.initQuality();
                tbEngineFactoryDynamic.setEngineFactoryQualityQ(quality);
                // 需求预测(已改为读表)
                tbEngineFactoryDynamic.setEngineFactoryDemandForecastD(CalculationUtils.demandForecast(
                        price[NumberEnum.PRICE_LOW_ARRAY_INDEX], price[NumberEnum.PRICE_UPPER_ARRAY_INDEX], quality,initMarketNeedNumber));
                // 创新概率
                tbEngineFactoryDynamic.setEngineFactoryInnovationProbability(RandomUtils.nextDouble(0, 1));
                tbEngineFactoryDynamic.setEngineFactoryInnovationTimes(0);
                // 加入集合中
                listEngineFactory.add(tbEngineFactory);
                mapEngineFactory.put(engineFactoryId, tbEngineFactory);
                listEngineFactoryDynamic.add(tbEngineFactoryDynamic);
                mapEngineFactoryDynamic.put(engineFactoryId, tbEngineFactoryDynamic);
            }
        }


        // 一类服务, 供应商的实际需求量之和
        int[] sumArrEngineFactoryNeedServiceNumberWithAlive = new int[5];
        // 一类服务, 供应商信誉度之和
        double[] sumArrSupplierCreditWithAlive = new double[5];
        // 一类服务, 供应商存活的数量
        int[] sumArrSupplierIsAlive = new int[5];


        for (TbEngineFactory aEngineFactory : listEngineFactory) {
            String engineFactoryId = aEngineFactory.getEngineFactoryId();
            TbEngineFactoryDynamic tbEngineFactoryDynamic1 = mapEngineFactoryDynamic.get(engineFactoryId);
            int engineFactoryDemandForecastD = Math.min(tbEngineFactoryDynamic1.getEngineFactoryDemandForecastD(),tbEngineFactoryDynamic1.getEngineFactoryCapacityM());
            sumArrEngineFactoryNeedServiceNumberWithAlive[0] += engineFactoryDemandForecastD;
            sumArrEngineFactoryNeedServiceNumberWithAlive[1] += engineFactoryDemandForecastD;
            sumArrEngineFactoryNeedServiceNumberWithAlive[2] += engineFactoryDemandForecastD;
            sumArrEngineFactoryNeedServiceNumberWithAlive[3] += engineFactoryDemandForecastD;
            sumArrEngineFactoryNeedServiceNumberWithAlive[4] += engineFactoryDemandForecastD;


        }

        // 存活的供应商中, 信誉度之和 存活个数计算
        for (TbSupplier tmpSupplier : listSupplier) {
            if (tmpSupplier.getSupplierAlive()) {
                String supplierId = tmpSupplier.getSupplierId();
                TbSupplierDynamic supplierDynamic = mapSupplierDynamic.get(supplierId);
                double supplierCredit = supplierDynamic.getSupplierCreditA();
                switch (tmpSupplier.getSupplierType()) {
                    case 210:
                        sumArrSupplierCreditWithAlive[0] += supplierCredit;
                        sumArrSupplierIsAlive[0]++;
                        break;
                    case 220:
                        sumArrSupplierCreditWithAlive[1] += supplierCredit;
                        sumArrSupplierIsAlive[1]++;
                        break;
                    case 230:
                        sumArrSupplierCreditWithAlive[2] += supplierCredit;
                        sumArrSupplierIsAlive[2]++;
                        break;
                    case 240:
                        sumArrSupplierCreditWithAlive[3] += supplierCredit;
                        sumArrSupplierIsAlive[3]++;
                        break;
                    case 250:
                        sumArrSupplierCreditWithAlive[4] += supplierCredit;
                        sumArrSupplierIsAlive[4]++;
                        break;
                    default:
                        throw new RuntimeException("no such type");
                }
            }

        }


        // 供应商的进入
        // 某类服务供应商产能之和
        int[] supplierTypeCode = {SupplierEnum.supplierType210, SupplierEnum.supplierType220, SupplierEnum.supplierType230
                , SupplierEnum.supplierType240, SupplierEnum.supplierType250};

        int[] sumArrSupplierCapacityWithAlive = new int[5];

        // 这个用来给某类供应商都死亡后的信誉度平均值
        double sumSupplierCapacityAllWithAlive = 0D;
        int sumSupplierNumberAllWithAlive = 0;
        for (TbSupplier tmpSupplier : listSupplier) {

            String supplierId = tmpSupplier.getSupplierId();
            TbSupplierDynamic aSupplierDynamic = mapSupplierDynamic.get(supplierId);

            if (tmpSupplier.getSupplierAlive()) {
                // 还活着的供应商
                sumSupplierCapacityAllWithAlive += aSupplierDynamic.getSupplierCreditA();
                sumSupplierNumberAllWithAlive++;

                int type = tmpSupplier.getSupplierType();
                int supplierCapacity = aSupplierDynamic.getSupplierCapacityM();
                switch (type) {
                    case 210:
                        sumArrSupplierCapacityWithAlive[0] += supplierCapacity;
                        break;
                    case 220:
                        sumArrSupplierCapacityWithAlive[1] += supplierCapacity;
                        break;
                    case 230:
                        sumArrSupplierCapacityWithAlive[2] += supplierCapacity;
                        break;
                    case 240:
                        sumArrSupplierCapacityWithAlive[3] += supplierCapacity;
                        break;
                    case 250:
                        sumArrSupplierCapacityWithAlive[4] += supplierCapacity;
                        break;
                    default:
                        throw new RuntimeException("no such type");
                }
            }
        }
        // 如果某类供应商都没了, 新生成的信誉度用这个
        double avgSupplierCapacityAllWithAlive = 0D;
        if (sumSupplierNumberAllWithAlive != 0) {
            // 还有活的供应商
            avgSupplierCapacityAllWithAlive = sumSupplierCapacityAllWithAlive / sumSupplierNumberAllWithAlive;
        } else {
            // 全都死了
            avgSupplierCapacityAllWithAlive = 1;
        }
        // 用来存储新生成的供应商ID
        Map<String, String> mapNewSupplierIdVsEngineFactoryIdWithHighestCredit = new HashMap<>(50);
        // 随机生成供应商
        for (int i = 0; i < sumArrSupplierIsAlive.length; i++) {
            double aveSupplierCreditTmp = 0;
            if (sumArrSupplierIsAlive[i] != 0) {
                // 某类供应商数量不为0
                aveSupplierCreditTmp = sumArrSupplierCreditWithAlive[i] * 1D / sumArrSupplierIsAlive[i];
            } else {
                aveSupplierCreditTmp = 1;
            }

            int engineFactoryNeedServiceNumberWithAlive = sumArrEngineFactoryNeedServiceNumberWithAlive[i];
            int supplierCapacity = sumArrSupplierCapacityWithAlive[i];
//            log.error("|||engineFactoryNeedServiceNumberWithAlive " + supplierTypeCode[i]+"  :  " + sumArrEngineFactoryNeedServiceNumberWithAlive[i]);
//            log.error("supplierCapacity " + supplierTypeCode[i]+"  :  " + sumArrSupplierCapacityWithAlive[i]);
//            log.error("|||supplierCapacity < engineFactoryNeedServiceNumberWithAlive : "+(supplierCapacity < engineFactoryNeedServiceNumberWithAlive));
            if (supplierCapacity < engineFactoryNeedServiceNumberWithAlive) {
                // 供应商产能 < 主机厂对该类服务的需求
                int tmp = RandomUtils.nextInt(3, 7);
//                log.info(supplierTypeCode[i]+" 生成供应商个数 "+tmp);
                for (int j = 0; j < tmp; j++) {
                    tbSupplier = new TbSupplier();
                    tbSupplier.setExperimentsNumber(experimentsNumber);
                    // 存活次数初始化
                    tbSupplier.setSupplierAliveTimes(0);
                    tbSupplier.setCycleTimes(cycleTime);
                    TbSupplierDynamic tbSupplierDynamic = new TbSupplierDynamic();
                    tbSupplierDynamic.setCycleTimes(cycleTime);

                    // 供应商id
                    String supplierId = CommonUtils.genId();
                    tbSupplier.setSupplierId(supplierId);

                    // 获取信誉度较大的一个主机厂动态数据
                    engineFactoryDynamicWithHighestCredit = arrEngineFactoryWith30HighestCredit[RandomUtils.nextInt(0, arrEngineFactoryWith30HighestCredit.length)];

                    mapNewSupplierIdVsEngineFactoryIdWithHighestCredit.put(supplierId, engineFactoryDynamicWithHighestCredit.getEngineFactoryId());

                    // 地理位置
                    tbEngineFactory = mapEngineFactory.get(engineFactoryDynamicWithHighestCredit.getEngineFactoryId());
//                    double[] position = genNewPosition(new double[]{tbEngineFactory.getEngineFactoryLocationGX(), tbEngineFactory.getEngineFactoryLocationGY()}, mapSupplierPosition);
                    double[] position;
                    if (i < tmp * 0.7) {
                        position = genNewPosition(new double[]{tbEngineFactory.getEngineFactoryLocationGX(), tbEngineFactory.getEngineFactoryLocationGY()}, mapSupplierPosition);
                    } else {
                        position = InitSupplierUtils.initPosition(mapSupplierPosition);
                    }
                    tbSupplier.setSupplierLocationGX(position[NumberEnum.POSITION_X_ARRAY_INDEX]);
                    tbSupplier.setSupplierLocationGY(position[NumberEnum.POSITION_Y_ARRAY_INDEX]);
                    // 供应商代码
                    tbSupplier.setSupplierType(InitSupplierUtils.initType(supplierTypeCode[i]));
                    // 每阶段固定成本
                    tbSupplier.setSupplierFixedCostC(InitSupplierUtils.initFixedCost());
                    tbSupplier.setSupplierAlive(true);
                    // 动态数据----------------
                    tbSupplierDynamic.setCycleTimes(cycleTime);
                    // 供应商id
                    tbSupplierDynamic.setSupplierId(supplierId);
                    // 初始总资产
                    tbSupplierDynamic.setSupplierTotalAssetsP(InitSupplierUtils.initTotalAssets());
                    // 信誉度
                    tbSupplierDynamic.setSupplierCreditA(aveSupplierCreditTmp);
                    // 最大产能
                    tbSupplierDynamic.setSupplierCapacityM(InitSupplierUtils.initCapacity());
                    // 价格
                    int[] price = InitSupplierUtils.initPrice();
                    tbSupplierDynamic.setSupplierPricePL(price[NumberEnum.PRICE_LOW_ARRAY_INDEX]);
                    tbSupplierDynamic.setSupplierPricePU(price[NumberEnum.PRICE_UPPER_ARRAY_INDEX]);
                    // 质量
                    tbSupplierDynamic.setSupplierQualityQs(InitSupplierUtils.initQuality());
                    // 创新概率
                    tbSupplierDynamic.setSupplierInnovationProbability(RandomUtils.nextDouble(0, 1));
                    tbSupplierDynamic.setSupplierInnovationTimes(0);
                    // 动态数据里的type
                    tbSupplierDynamic.setSupplierType(InitSupplierUtils.initType(supplierTypeCode[i]));
                    // 加入集合中
                    listSupplier.add(tbSupplier);
                    mapSupplier.put(supplierId, tbSupplier);
                    listSupplierDynamics.add(tbSupplierDynamic);
                    mapSupplierDynamic.put(supplierId, tbSupplierDynamic);
                }
            }
        }

        // 算两者距离, 存入map里
        Map<String, Double> mapDistance = new HashMap<>(100);
        for (TbEngineFactory aTbEngineFactory : listEngineFactory) {
            String engineFactoryId = aTbEngineFactory.getEngineFactoryId();
            double[] engineFactoryLocation = {aTbEngineFactory.getEngineFactoryLocationGX(), aTbEngineFactory.getEngineFactoryLocationGY()};
            for (TbSupplier aSupplier : listSupplier) {
                String supplierId = aSupplier.getSupplierId();
                String key = engineFactoryId + supplierId;

                double[] supplierLocation = {aSupplier.getSupplierLocationGX(), aSupplier.getSupplierLocationGY()};
                double value = CalculationUtils.calDistance(engineFactoryLocation, supplierLocation);
                mapDistance.put(key, value);
            }
        }


        // 关系强度, 都是重新生成的, 每个阶段生成, 历史数据用map读出来,
        // 下一个阶段要用的关系矩阵
        List<TbRelationMatrix> listNewRelationMatrix = new ArrayList<>();
        // 两个循环生成关系矩阵
        for (TbEngineFactory aTbEngineFactory : listEngineFactory) {
            // 主机厂是活着的
            // 主机厂id
            String engineFactoryId = aTbEngineFactory.getEngineFactoryId();
            for (TbSupplier aTbSupplier : listSupplier) {
                // 供应商是活着的
                TbRelationMatrix tbRelationMatrix = new TbRelationMatrix();
                tbRelationMatrix.setExperimentsNumber(experimentsNumber);
                tbRelationMatrix.setCycleTimes(cycleTime);
                tbRelationMatrix.setEngineFactoryId(engineFactoryId);
                String supplierId = aTbSupplier.getSupplierId();
                tbRelationMatrix.setSupplierId(supplierId);
                // 获取之前的数据
                String mapKey = engineFactoryId + supplierId;
                tbRelationMatrix.setMapKey(mapKey);
                TbRelationMatrix oldRelationMatrix = mapRelationshipMatrix2WithTbRelationMatrix.get(mapKey);
                if (oldRelationMatrix != null) {
                    // 原来有的
                    double relationScore = oldRelationMatrix.getRelationScore();
                    // 这里要根据关系强度来修改
                    // 距离大小
                    double distanceValue = mapDistance.get(mapKey);
                    if (distanceValue > 13) {
                        relationScore = relationScore - 1;
                    } else if (distanceValue > 9) {
                        relationScore = relationScore - 0.8;
                    } else if (distanceValue > 5) {
                        relationScore = relationScore - 0.3;
                    }
                    tbRelationMatrix.setRelationScore(relationScore);


                    tbRelationMatrix.setInitialRelationalDegree(oldRelationMatrix.getInitialRelationalDegree());
                    tbRelationMatrix.setAccumulativeTotalScore(oldRelationMatrix.getAccumulativeTotalScore());
                    tbRelationMatrix.setTransactionNumber(oldRelationMatrix.getTransactionNumber());
                } else {
                    // 要么工厂新增, 要么供应商新增
                    if (mapNewEngineFactoryIdVsSupplierIdWithHighestCredit.containsKey(engineFactoryId)) {
                        // 工厂是新增的
                        if (supplierId.equals(mapNewEngineFactoryIdVsSupplierIdWithHighestCredit.get(engineFactoryId))) {
                            // 供应商id 与 信誉度最高的供应商id相同, 设定初始值为0.2  (1~3个工厂会有0.2)
                            tbRelationMatrix.setRelationScore(0.2);
                            tbRelationMatrix.setInitialRelationalDegree(0.2);
                        } else {
                            // 供应商id 就是普通的供应商
                            double initRelationshipStrengthScore = InitRelationMatrixUtils.initRelationshipStrengthScore();
                            tbRelationMatrix.setRelationScore(initRelationshipStrengthScore);
                            tbRelationMatrix.setInitialRelationalDegree(initRelationshipStrengthScore);
                        }
                    } else {
                        // 供应商是新增的
                        if (engineFactoryId.equals(mapNewSupplierIdVsEngineFactoryIdWithHighestCredit.get(supplierId))) {
                            // 主机厂id 与 信誉度最高的主机厂id相同   (1~3个供应商会有0.2)  (只有新生的工厂或新生的供应商有0.2)
                            tbRelationMatrix.setRelationScore(0.2);
                            tbRelationMatrix.setInitialRelationalDegree(0.2);
                        } else {
                            // 主机厂id 就是普通的主机厂
                            double initRelationshipStrengthScore = InitRelationMatrixUtils.initRelationshipStrengthScore();
                            tbRelationMatrix.setRelationScore(initRelationshipStrengthScore);
                            tbRelationMatrix.setInitialRelationalDegree(initRelationshipStrengthScore);
                        }
                    }
                    // 补全其他的值
                    tbRelationMatrix.setAccumulativeTotalScore(0);
                    tbRelationMatrix.setTransactionNumber(0);
                }
                listNewRelationMatrix.add(tbRelationMatrix);
            }
        }


        // # 双方信誉度归一化
        // 退出后的厂还活着的
        double sumNewEngineFactoryCreditWithAlive = 0;
        for (TbEngineFactoryDynamic aEngineFactoryDynamic : listEngineFactoryDynamic) {
            String engineFactoryId = aEngineFactoryDynamic.getEngineFactoryId();
            if (mapEngineFactory.get(engineFactoryId).getEngineFactoryAlive()) {
                sumNewEngineFactoryCreditWithAlive += aEngineFactoryDynamic.getEngineFactoryCreditH();

            }
        }
        // 主机厂归一化
        for (TbEngineFactoryDynamic aEngineFactoryDynamic : listEngineFactoryDynamic) {
            aEngineFactoryDynamic.setEngineFactoryCreditH(aEngineFactoryDynamic.getEngineFactoryCreditH() / sumNewEngineFactoryCreditWithAlive);
        }

        // 供应商归一化
        double sumNewSupplierCreditWithAlive = 0;
        for (TbSupplierDynamic aSupplierDynamic : listSupplierDynamics) {
            String supplierId = aSupplierDynamic.getSupplierId();
            if (mapSupplier.get(supplierId).getSupplierAlive()) {
                sumNewSupplierCreditWithAlive += aSupplierDynamic.getSupplierCreditA();
            }
        }
        for (TbSupplierDynamic aSupplierDynamic : listSupplierDynamics) {
//            log.info("++++++++++++++++++");
//            log.info("sumNewSupplierCreditWithAlive   " + sumNewSupplierCreditWithAlive);
//            log.info("++++++++++++++++++");
            aSupplierDynamic.setSupplierCreditA(aSupplierDynamic.getSupplierCreditA() / sumNewSupplierCreditWithAlive);
        }

        // 计算并生成每类供应商的平均价格和平均质量
        // 生成并插入tb_supplier_type_avg表里
        calSupplierTypeAvgPriceAndQuality(experimentsNumber, cycleTime, listOrderPlus, mapSupplier);

        // 算出每个二级供应商的实际平均价格和实际平均质量
        setSupplierAvgPriceAndAvgQuality(listSupplierDynamics, listOrderPlus, mapSupplierDynamic);
        // 计算一级市场供需情况和二级市场供需情况
        calBalance(experimentsNumber, cycleTime, listEngineFactoryFinalProvisions, listEngineFactoryDynamic, mapEngineFactory, listOrderPlus, listSupplierDynamics, mapSupplier);

        for (TbEngineFactory aEngineFactory : listEngineFactory) {
            aEngineFactory.setCycleTimes(cycleTime);
            aEngineFactory.setEngineFactoryAliveTimes(aEngineFactory.getEngineFactoryAliveTimes() + 1);
            TbEngineFactoryDynamic tbEngineFactoryDynamic1 = mapEngineFactoryDynamic.get(aEngineFactory.getEngineFactoryId());
            tbEngineFactoryDynamic1.setCycleTimes(cycleTime);
            tbEngineFactoryDynamic1.setExperimentsNumber(experimentsNumber);
            // 需求预测
            tbEngineFactoryDynamic1.setEngineFactoryDemandForecastD(
                    CalculationUtils.demandForecast(
                            tbEngineFactoryDynamic1.getEngineFactoryPricePL(), tbEngineFactoryDynamic1.getEngineFactoryPricePU()
                            , tbEngineFactoryDynamic1.getEngineFactoryQualityQ(), initMarketNeedNumber));

        }
        for (TbSupplier aSupplier : listSupplier) {
            aSupplier.setCycleTimes(cycleTime);
            aSupplier.setSupplierAliveTimes(aSupplier.getSupplierAliveTimes() + 1);
            TbSupplierDynamic supplierDynamic = mapSupplierDynamic.get(aSupplier.getSupplierId());
            supplierDynamic.setCycleTimes(cycleTime);
            supplierDynamic.setExperimentsNumber(experimentsNumber);
        }

        storeTransactionContract(experimentsNumber, cycleTime, listTransactionContract);

        // 此时已经完成进入和退出
        storeIntoDatabase(listEngineFactory, listEngineFactoryDynamic, listSupplier, listSupplierDynamics, listNewRelationMatrix);

    }

    /**
     * 淘汰主机厂和供应商
     *
     * @param listEngineFactoryDynamic 主机厂动态数据集合
     * @param mapEngineFactory         主机厂映射
     * @param listSupplierDynamic      供应商动态数据集合
     * @param mapSupplier              供应商映射
     */
    private void eliminationEngineFactoryAndSupplier(
            List<TbEngineFactoryDynamic> listEngineFactoryDynamic
            , Map<String, TbEngineFactory> mapEngineFactory, List<TbSupplierDynamic> listSupplierDynamic, Map<String, TbSupplier> mapSupplier) {

        // 要淘汰的主机厂
        Set<TbEngineFactory> setEngineFactory = new HashSet<>();
        // 要淘汰的供应商
        Set<TbSupplier> setSupplier = new HashSet<>();

        // 主机厂(价格/质量)淘汰高的30%
        Queue<TbEngineFactoryDynamic> queueEngineFactoryDynamicTmp1 = new PriorityQueue<>(((o1, o2) ->
                (o2.getEngineFactoryPricePL()+o2.getEngineFactoryPricePU()) /2d / o2.getEngineFactoryQualityQ()
                        - (o1.getEngineFactoryPricePL()+o1.getEngineFactoryPricePU()) / 2d / o1.getEngineFactoryQualityQ() >= 0 ? 1 : -1));
        // 主机厂和供应商信誉度淘汰10%小的
        Queue<TbEngineFactoryDynamic> queueEngineFactoryDynamicTmp2 = new PriorityQueue<>(((o1, o2) ->
                o1.getEngineFactoryCreditH() - o2.getEngineFactoryCreditH() >= 0 ? 1 : -1));
        Queue<TbSupplierDynamic> queueSupplierDynamic = new PriorityQueue<>(((o1, o2) ->
                o1.getSupplierCreditA() - o2.getSupplierCreditA() > 0 ? 1 : -1));
        // 主机厂
        for (TbEngineFactoryDynamic aEngineFactoryDynamic : listEngineFactoryDynamic) {
            if (mapEngineFactory.get(aEngineFactoryDynamic.getEngineFactoryId()).getEngineFactoryAlive()) {
                queueEngineFactoryDynamicTmp1.add(aEngineFactoryDynamic);
                queueEngineFactoryDynamicTmp2.add(aEngineFactoryDynamic);
            }
        }
        // 供应商
        for (TbSupplierDynamic aSupplierDynamic : listSupplierDynamic) {
            if (mapSupplier.get(aSupplierDynamic.getSupplierId()).getSupplierAlive()) {
                queueSupplierDynamic.add(aSupplierDynamic);
            }
        }

        //---测试
//        log.info("Test queueEngineFactoryDynamicTmp2");
//        while (queueEngineFactoryDynamicTmp2.peek() != null) {
//            log.info(queueEngineFactoryDynamicTmp2.poll().getEngineFactoryCreditH().toString());
//        }
//        log.info("");
//        log.info("");
        //---
//
//
        // 主机厂与供应商应该淘汰的个数
        int engineFactoryDynamicShouldEliminationNumber = (int) (queueEngineFactoryDynamicTmp1.size() * 0.3);
        int supplierShouldEliminationNumber = (int) (queueSupplierDynamic.size() * 0.1);
        for (int i = 0; i < engineFactoryDynamicShouldEliminationNumber; i++) {
            TbEngineFactoryDynamic engineFactoryDynamicTmp1 = queueEngineFactoryDynamicTmp1.poll();
            TbEngineFactoryDynamic engineFactoryDynamicTmp2 = queueEngineFactoryDynamicTmp2.poll();
            setEngineFactory.add(mapEngineFactory.get(engineFactoryDynamicTmp1.getEngineFactoryId()));
            setEngineFactory.add(mapEngineFactory.get(engineFactoryDynamicTmp2.getEngineFactoryId()));
        }
        for (int i = 0; i < supplierShouldEliminationNumber; i++) {
            setSupplier.add(mapSupplier.get(queueSupplierDynamic.poll().getSupplierId()));
        }

        // 主机厂与供应商设置为false
        for (TbEngineFactory engineFactory : setEngineFactory) {
            engineFactory.setEngineFactoryAlive(false);
        }
        for (TbSupplier aSupplier : setSupplier) {
            aSupplier.setSupplierAlive(false);
        }

    }


    /**
     * 生成还存活的供应商及主机厂的位置
     *
     * @param mapEngineFactoryPosition 存入的主机厂位置
     * @param listEngineFactory        主机厂静态集合
     * @param mapSupplierPosition      存入的供应商位置
     * @param listSupplier             供应商静态数据集合
     */
    private void genMapEngineFactoryPositionAmdMapSupplierPosition(Map<Double, Double> mapEngineFactoryPosition, List<TbEngineFactory> listEngineFactory, Map<Double, Double> mapSupplierPosition, List<TbSupplier> listSupplier) {
        for (TbEngineFactory aEngineFactory : listEngineFactory) {
            if (aEngineFactory.getEngineFactoryAlive()) {
                mapEngineFactoryPosition.put(aEngineFactory.getEngineFactoryLocationGX(), aEngineFactory.getEngineFactoryLocationGY());
            }
        }
        for (TbSupplier aSupplier : listSupplier) {
            if (aSupplier.getSupplierAlive()) {
                mapSupplierPosition.put(aSupplier.getSupplierLocationGX(), aSupplier.getSupplierLocationGY());
            }
        }
    }

    /**
     * 生成前30%信誉度最高的供应商
     *
     * @param listSupplier       供应商集合
     * @param mapSupplierDynamic 供应商动态数据集合映射
     * @return 信誉度前30%的供应商集合
     */
    private TbSupplierDynamic[] genSupplierWith30HighestCredit(List<TbSupplier> listSupplier, Map<String, TbSupplierDynamic> mapSupplierDynamic) {
        Queue<TbSupplierDynamic> queueSupplierDynamic = new PriorityQueue<>((o1, o2) -> {
            double v = o2.getSupplierCreditA() - o1.getSupplierCreditA();
            return v >= 0 ? 1 : -1;
        });
        for (TbSupplier aSupplier : listSupplier) {
            if (aSupplier.getSupplierAlive()) {
                queueSupplierDynamic.add(mapSupplierDynamic.get(aSupplier.getSupplierId()));
            }
        }
        int tmp = (int) Math.round(queueSupplierDynamic.size() * 0.3);
        TbSupplierDynamic[] arrSupplierDynamic = new TbSupplierDynamic[tmp];
        for (int i = 0; i < tmp; i++) {
            arrSupplierDynamic[i] = queueSupplierDynamic.poll();
        }
        return arrSupplierDynamic;
    }

    /**
     * 生成前30%信誉度最高的主机厂
     *
     * @param listEngineFactory       主机厂集合
     * @param mapEngineFactoryDynamic 主机厂动态数据集合映射
     * @return 信誉度前30%的厂集合
     */
    private TbEngineFactoryDynamic[] genEngineFactoryWith30HighestCredit(List<TbEngineFactory> listEngineFactory, Map<String, TbEngineFactoryDynamic> mapEngineFactoryDynamic) {
        Queue<TbEngineFactoryDynamic> queueEngineFactoryDynamics = new PriorityQueue<>((o1, o2) -> {
            double v = o2.getEngineFactoryCreditH() - o1.getEngineFactoryCreditH();
            return v >= 0 ? 1 : -1;
        });
        for (TbEngineFactory aEngineFactory : listEngineFactory) {
            if (aEngineFactory.getEngineFactoryAlive()) {
                queueEngineFactoryDynamics.add(mapEngineFactoryDynamic.get(aEngineFactory.getEngineFactoryId()));
            }
        }
        int tmp = (int) Math.round(queueEngineFactoryDynamics.size() * 0.3);
        tmp = tmp <= 0 ? 1 : tmp;
        TbEngineFactoryDynamic[] arrEngineFactoryDynamic = new TbEngineFactoryDynamic[tmp];
        for (int i = 0; i < tmp; i++) {
            arrEngineFactoryDynamic[i] = queueEngineFactoryDynamics.poll();
        }
        return arrEngineFactoryDynamic;
    }

    /**
     * 存交易契约
     *
     * @param experimentsNumber       实验次数
     * @param cycleTime               循环次数
     * @param listTransactionContract 交易契约集合
     */
    private void storeTransactionContract(int experimentsNumber, int cycleTime, ArrayList<TransactionContract> listTransactionContract) {
        List<TbTransactionContract> listRes = new ArrayList<>();
        for (TransactionContract aTransactionContract : listTransactionContract) {
            TbTransactionContract tbTransactionContract = new TbTransactionContract();
            BeanUtils.copyProperties(aTransactionContract, tbTransactionContract);
            tbTransactionContract.setExperimentsNumber(experimentsNumber);
            tbTransactionContract.setCycleTimes(cycleTime);
            listRes.add(tbTransactionContract);
//        tbTransactionContractMapper.insertSelective(tbTransactionContract);
        }
        tbTransactionContractMapper.insertList(listRes);
    }

    /**
     * 计算一级市场供需情况和二级市场供需情况
     *
     * @param experimentsNumber                实验次数
     * @param cycleTime                        循环次数
     * @param listEngineFactoryFinalProvisions 市场最终成交订单
     * @param listEngineFactoryDynamic         主机厂动态数据集合
     * @param mapEngineFactory                 主机厂静态映射
     * @param listOrderPlus                    主机与供应商订单
     * @param listSupplierDynamics             供应商动态数据集合
     * @param mapSupplier                      供应商映射
     */
    private void calBalance(int experimentsNumber, int cycleTime, List<EngineFactoryFinalProvision> listEngineFactoryFinalProvisions, List<TbEngineFactoryDynamic> listEngineFactoryDynamic, Map<String, TbEngineFactory> mapEngineFactory, List<OrderPlus> listOrderPlus, List<TbSupplierDynamic> listSupplierDynamics, Map<String, TbSupplier> mapSupplier) {
        TbBalance tbBalance = new TbBalance();
        tbBalance.setExperimentsNumber(experimentsNumber);
        tbBalance.setCycleTimes(cycleTime);
        // 一级市场供需平衡
        int sumActualSaleNumber = 0;
        int sumEngineFactoryCapacity = 0;
        for (EngineFactoryFinalProvision aEngineFactoryFinalProvision : listEngineFactoryFinalProvisions) {
            String engineFactoryId = aEngineFactoryFinalProvision.getEngineFactoryId();
            TbEngineFactory engineFactory = mapEngineFactory.get(engineFactoryId);
            if (engineFactory.getEngineFactoryAlive()) {
                sumActualSaleNumber += aEngineFactoryFinalProvision.getActualSaleNumber();
            }
        }
        for (TbEngineFactoryDynamic aEngineFactoryDynamic : listEngineFactoryDynamic) {
            String engineFactoryId = aEngineFactoryDynamic.getEngineFactoryId();
            TbEngineFactory engineFactory = mapEngineFactory.get(engineFactoryId);
            if (engineFactory.getEngineFactoryAlive()) {
                sumEngineFactoryCapacity += aEngineFactoryDynamic.getEngineFactoryCapacityM();
            }
        }
        tbBalance.setEngineFactoryBalance(sumActualSaleNumber * 1.0 / sumEngineFactoryCapacity);
        // 二级市场供需平衡
        int sumSupplierActualNumber = 0;
        for (OrderPlus aOrderPlus : listOrderPlus) {
            String supplierId = aOrderPlus.getSupplierId();
            TbSupplier tbSupplier = mapSupplier.get(supplierId);
            if (tbSupplier.getSupplierAlive()) {
                sumSupplierActualNumber += aOrderPlus.getSupplierActualNumberM();
            }
        }
        int sumSupplierCapacity = 0;
        for (TbSupplierDynamic aSupplierDynamic : listSupplierDynamics) {
            String supplierId = aSupplierDynamic.getSupplierId();
            TbSupplier tbSupplier = mapSupplier.get(supplierId);
            if (tbSupplier.getSupplierAlive()) {
                sumSupplierCapacity += aSupplierDynamic.getSupplierCapacityM();
            }
        }
        tbBalance.setSupplierBalance(sumSupplierActualNumber * 1.0 / sumSupplierCapacity);

        tbBalanceMapper.insertSelective(tbBalance);
    }

    /**
     * 计算并生成每类供应商的平均价格和平均质量
     * 生成并插入tb_supplier_type_avg表里
     *
     * @param experimentsNumber 实验次数
     * @param cycleTime         循环次数
     * @param listOrderPlus     主机厂与供应商交易结果
     * @param mapSupplier       供应商静态数据集合
     */
    private void calSupplierTypeAvgPriceAndQuality(int experimentsNumber, int cycleTime, List<OrderPlus> listOrderPlus, Map<String, TbSupplier> mapSupplier) {
        ArrayList<TbSupplierTypeAvg> listSupplierTypeAvgArray = new ArrayList<>();
        // 供应商类型 - 实际金额
        Map<Integer, Integer> mapTmpPriceSum = new HashMap<>(listOrderPlus.size());
        // 供应商类型 - 质量
        Map<Integer, Integer> mapTmpQualitySum = new HashMap<>(listOrderPlus.size());
        // 供应商类型 - 实际交易数量
        Map<Integer, Integer> mapTmpNumberSum = new HashMap<>(listOrderPlus.size());
        for (OrderPlus orderPlus : listOrderPlus) {
            String supplierId = orderPlus.getSupplierId();
            TbSupplier tbSupplier = mapSupplier.get(supplierId);
            int supplierType = tbSupplier.getSupplierType();

            mapTmpPriceSum.putIfAbsent(supplierType, 0);
            mapTmpQualitySum.putIfAbsent(supplierType, 0);
            mapTmpNumberSum.putIfAbsent(supplierType, 0);

            int priceSum = mapTmpPriceSum.get(supplierType);
            int qualitySum = mapTmpQualitySum.get(supplierType);
            int numberSum = mapTmpNumberSum.get(supplierType);

            priceSum += orderPlus.getSupplierActualPriceP();
            mapTmpPriceSum.put(supplierType, priceSum);

            qualitySum += orderPlus.getSupplierActualQualityQs();
            mapTmpQualitySum.put(supplierType, qualitySum);

            numberSum++;
            mapTmpNumberSum.put(supplierType, numberSum);

        }
        for (Map.Entry<Integer, Integer> entry : mapTmpNumberSum.entrySet()) {
            int supplierType = entry.getKey();
            int numberSum = entry.getValue();

            int priceSum = mapTmpPriceSum.get(supplierType);
            int qualitySum = mapTmpQualitySum.get(supplierType);

            TbSupplierTypeAvg tbSupplierTypeAvg = new TbSupplierTypeAvg();
            tbSupplierTypeAvg.setExperimentsNumber(experimentsNumber);
            tbSupplierTypeAvg.setCycleTimes(cycleTime);
            tbSupplierTypeAvg.setSupplierType(supplierType);
            tbSupplierTypeAvg.setAvgActurePrice((int) (priceSum * 1.0 / numberSum));
            tbSupplierTypeAvg.setAvgActureQuality((int) (qualitySum * 1.0 / numberSum));

            listSupplierTypeAvgArray.add(tbSupplierTypeAvg);
        }


        tbSupplierTypeAvgMapper.insertList(listSupplierTypeAvgArray);
    }

    /**
     * 算出并设置每个二级供应商的实际平均价格和实际平均质量
     *
     * @param listSupplierDynamics 供应商动态数据
     * @param listOrderPlus        主机厂与供应商交易结果
     * @param mapSupplierDynamic   供应商动态数据
     */
    private void setSupplierAvgPriceAndAvgQuality(List<TbSupplierDynamic> listSupplierDynamics, List<OrderPlus> listOrderPlus, Map<String, TbSupplierDynamic> mapSupplierDynamic) {
        // 供应商id - 实际金额
        Map<String, Integer> mapTmpPriceSum = new HashMap<>(listOrderPlus.size());
        // 供应商id - 质量
        Map<String, Integer> mapTmpQualitySum = new HashMap<>(listOrderPlus.size());
        // 供应商id - 实际交易单数
        Map<String, Integer> mapTmpNumberSum = new HashMap<>(listOrderPlus.size());
        for (OrderPlus orderPlus : listOrderPlus) {
            String supplierId = orderPlus.getSupplierId();

            mapTmpPriceSum.putIfAbsent(supplierId, 0);
            mapTmpQualitySum.putIfAbsent(supplierId, 0);
            mapTmpNumberSum.putIfAbsent(supplierId, 0);


            int priceSum = mapTmpPriceSum.get(supplierId);
            int qualitySum = mapTmpQualitySum.get(supplierId);
            int numberSum = mapTmpNumberSum.get(supplierId);

            priceSum += orderPlus.getSupplierActualPriceP();
            mapTmpPriceSum.put(supplierId, priceSum);

            qualitySum += orderPlus.getSupplierActualQualityQs();
            mapTmpQualitySum.put(supplierId, qualitySum);

            numberSum++;
            mapTmpNumberSum.put(supplierId, numberSum);
        }

        for (TbSupplierDynamic aSupplierDynamic : listSupplierDynamics) {
            String supplierId = aSupplierDynamic.getSupplierId();
            TbSupplierDynamic supplierDynamic = mapSupplierDynamic.get(supplierId);
            if (mapTmpPriceSum.containsKey(supplierId)) {
                int priceSum = mapTmpPriceSum.get(supplierId);
                int qualitySum = mapTmpQualitySum.get(supplierId);
                int numberSum = mapTmpNumberSum.get(supplierId);
                supplierDynamic.setAvgPrice((int) (priceSum * 1.0 / numberSum));
                supplierDynamic.setAvgQuality((int) (qualitySum * 1.0 / numberSum));
            }
        }
    }

    /**
     * 将更新计算好后的数据存入数据库
     *
     * @param listEngineFactory        主机厂静态数据
     * @param listEngineFactoryDynamic 主机厂动态数据
     * @param listSupplier             供应商静态数据
     * @param listSupplierDynamics     供应商动态数据
     * @param listNewRelationMatrix    主机厂与供应商之间的关系矩阵
     */
    private void storeIntoDatabase(List<TbEngineFactory> listEngineFactory, List<TbEngineFactoryDynamic> listEngineFactoryDynamic, List<TbSupplier> listSupplier, List<TbSupplierDynamic> listSupplierDynamics, List<TbRelationMatrix> listNewRelationMatrix) {
        tbEngineFactoryMapper.insertList(listEngineFactory);
        tbEngineFactoryDynamicMapper.insertList(listEngineFactoryDynamic);
        tbSupplierMapper.insertList(listSupplier);
        tbSupplierDynamicMapper.insertList(listSupplierDynamics);
        tbRelationMatrixMapper.insertList(listNewRelationMatrix);
    }

    /**
     * 用于填充供应商动态数据map
     *
     * @param listSupplierDynamics 供应商动态数据集合
     * @return 供应商id - 供应商对应的动态数据
     */
    private Map<String, TbSupplierDynamic> getMapSupplierIdVsSupplierDynamic(List<TbSupplierDynamic> listSupplierDynamics) {
        Map<String, TbSupplierDynamic> mapEngineFactory = new HashMap<>(100);
        for (TbSupplierDynamic aSupplierDynamic : listSupplierDynamics) {
            String supplierId = aSupplierDynamic.getSupplierId();
            mapEngineFactory.put(supplierId, aSupplierDynamic);
        }
        return mapEngineFactory;
    }

    /**
     * 用于填充主机厂动态数据map
     *
     * @param listEngineFactoryDynamic 主机厂动态数据集合
     * @return 主机厂id - 主机厂对应的动态数据
     */
    private Map<String, TbEngineFactoryDynamic> getMapEngineFactoryIdVsEngineFactoryDynamic(List<TbEngineFactoryDynamic> listEngineFactoryDynamic) {
        Map<String, TbEngineFactoryDynamic> mapEngineFactoryDynamic = new HashMap<>(100);
        for (TbEngineFactoryDynamic aEngineFactoryDynamic : listEngineFactoryDynamic) {
            String engineFactoryId = aEngineFactoryDynamic.getEngineFactoryId();
            mapEngineFactoryDynamic.put(engineFactoryId, aEngineFactoryDynamic);
        }
        return mapEngineFactoryDynamic;
    }

    /**
     * 把供应商id 和 对应的供应商静态数据 存入map中
     *
     * @param listSupplier 供应商静态数据集合
     * @return 供应商id - 供应商静态数据
     */
    private Map<String, TbSupplier> getMapSupplierIdVsSupplier(List<TbSupplier> listSupplier) {
        Map<String, TbSupplier> mapSupplier = new HashMap<>(100);
        for (TbSupplier aSupplier : listSupplier) {
            String supplierId = aSupplier.getSupplierId();
            mapSupplier.put(supplierId, aSupplier);
        }
        return mapSupplier;
    }

    /**
     * 把主机厂id 与 主机厂静态数据 存入map中
     *
     * @param listEngineFactory 主机厂静态数据集合
     * @return 主机厂id - 主机厂静态数据
     */
    private Map<String, TbEngineFactory> getMapEngineFactoryIdVsEngineFactory(List<TbEngineFactory> listEngineFactory) {
        Map<String, TbEngineFactory> mapEngineFactory = new HashMap<>(100);
        for (TbEngineFactory aEngineFactory : listEngineFactory) {
            String engineFactoryId = aEngineFactory.getEngineFactoryId();
            mapEngineFactory.put(engineFactoryId, aEngineFactory);
        }
        return mapEngineFactory;
    }

    /**
     * 对供应商计算并设置产能利用率,
     * 调整产能/价格/质量
     *
     * @param listSupplierDynamics                   供应商动态数据集合
     * @param avgFinalMarketPrice                    平均市场价
     * @param avgArrSupplierQuality                  平均质量数组
     * @param mapSupplierIdVsTransactionContract
     * @param listListEngineFactoryTaskDecomposition
     */
    private void calAndSetSupplierCapacityUtilizationAndAdjustCapacityPriceQuality(
            List<TbSupplierDynamic> listSupplierDynamics
            , double avgFinalMarketPrice
            , int[] avgArrSupplierQuality
            , HashMap<String, List<TransactionContract>> mapSupplierIdVsTransactionContract
            , ArrayList<ArrayList<EngineFactoryManufacturingTask>> listListEngineFactoryTaskDecomposition) {

        // 主机厂任务量最小值(发布任务之前的)
        int minEngineFactoryNeedServiceNumber = Integer.MAX_VALUE;

        for (ArrayList<EngineFactoryManufacturingTask> aEngineFactoryManufacturingTaskArrayList : listListEngineFactoryTaskDecomposition) {
            for (EngineFactoryManufacturingTask aFactoryManufacturingTask : aEngineFactoryManufacturingTaskArrayList) {
                int engineFactoryNeedServiceNumber = aFactoryManufacturingTask.getEngineFactoryNeedServiceNumber();
                // 算出主机厂任务量最小值
                minEngineFactoryNeedServiceNumber = Math.min(minEngineFactoryNeedServiceNumber, engineFactoryNeedServiceNumber);
            }
        }


        for (TbSupplierDynamic aSupplierDynamic : listSupplierDynamics) {
            // 阶段开始时初始产能
            int initSupplierCapacity = aSupplierDynamic.getSupplierCapacityM();
            // 计算利用率并更新
            // 某类供应总需求
            int sumSupplierOrderNumber = 0;


            // 某类平均需求数量
            double avgSupplierOrderNumber = 0;
            // 某类供应质量
            int avgSupplierQuality = 0;
            int supplierType = aSupplierDynamic.getSupplierType();
            switch (supplierType) {
                case 210:
                    avgSupplierQuality = avgArrSupplierQuality[0];
                    break;
                case 220:
                    avgSupplierQuality = avgArrSupplierQuality[1];
                    break;
                case 230:
                    avgSupplierQuality = avgArrSupplierQuality[2];
                    break;
                case 240:
                    avgSupplierQuality = avgArrSupplierQuality[3];
                    break;
                case 250:
                    avgSupplierQuality = avgArrSupplierQuality[4];
                    break;
                default:
                    throw new RuntimeException("no such type");
            }
            String supplierId = aSupplierDynamic.getSupplierId();
            if (mapSupplierIdVsTransactionContract.containsKey(supplierId)) {
                List<TransactionContract> listTransactionContract = mapSupplierIdVsTransactionContract.get(supplierId);
                for (TransactionContract aTransactionContract : listTransactionContract) {
                    int engineFactoryNeedServiceNumber = aTransactionContract.getEngineFactoryNeedServiceNumber();
                    sumSupplierOrderNumber += engineFactoryNeedServiceNumber;
                }
            }

            // 计算产能利用率
            double supplierCapacityUtilization = sumSupplierOrderNumber * 1D / aSupplierDynamic.getSupplierCapacityM();
            // 更新
            aSupplierDynamic.setSupplierCapacityUtilization(supplierCapacityUtilization);

            // 供应商产能
            int supplierCapacity = aSupplierDynamic.getSupplierCapacityM();

            // 供应商原来价格下限
            int supplierPricePL = aSupplierDynamic.getSupplierPricePL();
            // 供应商原来价格上限
            Integer supplierPricePU = aSupplierDynamic.getSupplierPricePU();
            // 供应商原来平均价
            double initAvgSupplierPrice = (supplierPricePL + supplierPricePU) / 2.0;

            if (supplierCapacityUtilization >= 0.8) {
                // 产品成交的平均质量
                // 利用率为1(供不应求)
                if (initAvgSupplierPrice >= avgSupplierOrderNumber) {
                    // 初始价格的平均价 >= 所有成交价格的平均值
                    // 调整产能
                    supplierCapacity = (int) Math.round(supplierCapacity * 1.5);
                    // 更新下一阶段的产能
                    aSupplierDynamic.setSupplierCapacityM(supplierCapacity);
                } else {
                    // 初始价格的平均价 < 所有成交价格的平均值
                    // 调整价格区间并更新
                    if (supplierPricePL < initAvgSupplierPrice && initAvgSupplierPrice < supplierPricePU) {
                        aSupplierDynamic.setSupplierPricePL(RandomUtils.nextInt(supplierPricePL, (int) initAvgSupplierPrice + 1));
                        aSupplierDynamic.setSupplierPricePU(RandomUtils.nextInt((int) initAvgSupplierPrice, supplierPricePU + 1));
                    } else {
                        aSupplierDynamic.setSupplierPricePL((int) Math.round(initAvgSupplierPrice / 2.0));
                        aSupplierDynamic.setSupplierPricePU((int) Math.round(initAvgSupplierPrice * 1.5));
                    }
                }
            } else {
                // 利用率 < 1(供过于求)
                if (minEngineFactoryNeedServiceNumber <= supplierCapacity) {
                    // 主机厂最小的任务量 <= 供应商产能
                    if (initAvgSupplierPrice >= avgFinalMarketPrice) {
                        // 初始价格的平均价 >= 所有成交价格的平均值
                        if (supplierPricePL < initAvgSupplierPrice) {
                            aSupplierDynamic.setSupplierPricePL(RandomUtils.nextInt(supplierPricePL, (int) initAvgSupplierPrice + 1));
                            aSupplierDynamic.setSupplierPricePU(RandomUtils.nextInt((int) initAvgSupplierPrice, supplierPricePU + 1));
                        } else {
                            aSupplierDynamic.setSupplierPricePL((int) Math.round(initAvgSupplierPrice / 2.0));
                            aSupplierDynamic.setSupplierPricePU((int) Math.round(initAvgSupplierPrice * 1.5));
                        }
                    } else {
                        // 初始价格的平均价 < 所有成交价格的平均值
                        int supplierQuality = aSupplierDynamic.getSupplierQualityQs();
                        if (supplierQuality >= avgSupplierQuality) {
                            // 质量 >= 平均质量
                            // 调整产能
                            supplierCapacity = (int) Math.round(supplierCapacity * 0.9);
                            // 更新下一阶段的产能
                            aSupplierDynamic.setSupplierCapacityM(supplierCapacity);
                        } else {
                            // 质量 < 平均质量
                            if (supplierQuality < 10) {
                                supplierQuality++;
                                aSupplierDynamic.setSupplierQualityQs(supplierQuality);
                                // 更新总资产
                                aSupplierDynamic.setSupplierTotalAssetsP((int) Math.round(aSupplierDynamic.getSupplierTotalAssetsP() * 0.9));
                            }
                        }
                    }
                } else {
                    // 供应商产能 * 1.1
                    supplierCapacity = (int) Math.round(supplierCapacity * 1.1);
                    // 更新下一阶段的产能
                    aSupplierDynamic.setSupplierCapacityM(supplierCapacity);
                }
            }
        }
    }

    /**
     * 计算供应商一类服务的市场需求量/服务商个数/平均需求/质量/平均质量
     *
     * @param listTransactionContract   交易契约集合
     * @param sumArrSupplierOrderNumber 该类服务主机厂需求量
     * @param sumArrSupplierNumber      该类供应商数量
     * @param avgArrSupplierOrderNumber 该类服务平均需求量
     * @param sumArrSupplierQuality     该类服务总质量
     * @param avgArrSupplierQuality     该类服务平均质量
     */
    private void calArrAvgNumberAndQuantity(ArrayList<TransactionContract> listTransactionContract, int[] sumArrSupplierOrderNumber, int[] sumArrSupplierNumber, double[] avgArrSupplierOrderNumber, int[] sumArrSupplierQuality, int[] avgArrSupplierQuality) {
        for (TransactionContract aTransactionContract : listTransactionContract) {
            int taskType = aTransactionContract.getTaskType();
            int supplierOrderNumber = aTransactionContract.getEngineFactoryNeedServiceNumber();
            int orderQuality = aTransactionContract.getOrderQuality();
            switch (taskType) {
                case 210:
                    sumArrSupplierOrderNumber[0] += supplierOrderNumber;
                    sumArrSupplierNumber[0]++;
                    sumArrSupplierQuality[0] += orderQuality;
                    break;
                case 220:
                    sumArrSupplierOrderNumber[1] += supplierOrderNumber;
                    sumArrSupplierNumber[1]++;
                    sumArrSupplierQuality[1] += orderQuality;
                    break;
                case 230:
                    sumArrSupplierOrderNumber[2] += supplierOrderNumber;
                    sumArrSupplierNumber[2]++;
                    sumArrSupplierQuality[2] += orderQuality;
                    break;
                case 240:
                    sumArrSupplierOrderNumber[3] += supplierOrderNumber;
                    sumArrSupplierNumber[3]++;
                    sumArrSupplierQuality[3] += orderQuality;
                    break;
                case 250:
                    sumArrSupplierOrderNumber[4] += supplierOrderNumber;
                    sumArrSupplierNumber[4]++;
                    sumArrSupplierQuality[4] += orderQuality;
                    break;
                default:
                    throw new RuntimeException("no such type");
            }
        }
        // 算下平均值
        for (int i = 0; i < avgArrSupplierOrderNumber.length; i++) {
            // 供应商一类服务数量平均值
            avgArrSupplierOrderNumber[i] = sumArrSupplierOrderNumber[i] * 1D / sumArrSupplierNumber[i];
            // 供应商一类服务质量平均值
            avgArrSupplierQuality[i] = (int) Math.round(sumArrSupplierQuality[i] * 1D / sumArrSupplierNumber[i]);
        }
    }

    /**
     * 对主机厂计算并设置产能利用率,
     * 调整产能/价格/质量
     *
     * @param listEngineFactoryDynamic                 主机厂动态数据集合
     * @param mapEngineIdVsEngineFactoryFinalProvision 主机厂与市场实际交易集合
     * @param avgFinalMarketPrice                      所有产品的平均价格
     * @param avgFinalMarketQuality                    所有产品的平均质量
     */
    private void calAndSetEngineFactoryCapacityUtilizationAndAdjustCapacityPriceQuality(
            List<TbEngineFactoryDynamic> listEngineFactoryDynamic
            , HashMap<String, EngineFactoryFinalProvision> mapEngineIdVsEngineFactoryFinalProvision
            , double avgFinalMarketPrice
            , double avgFinalMarketQuality) {

        for (TbEngineFactoryDynamic aEngineFactoryDynamic : listEngineFactoryDynamic) {
            String engineFactoryId = aEngineFactoryDynamic.getEngineFactoryId();
            EngineFactoryFinalProvision engineFactoryFinalProvision = mapEngineIdVsEngineFactoryFinalProvision.get(engineFactoryId);
            // 实际销量
            int actualSaleNumber = 0;
            if (engineFactoryFinalProvision != null) {
                actualSaleNumber = engineFactoryFinalProvision.getActualSaleNumber();
            }
            // 主机产能
            int engineFactoryCapacity = aEngineFactoryDynamic.getEngineFactoryCapacityM();
            // 计算利用率并更新
            double engineFactoryCapacityUtilization = actualSaleNumber * 1D / engineFactoryCapacity;
            aEngineFactoryDynamic.setEngineFactoryCapacityUtilization(engineFactoryCapacityUtilization);


            // 主机厂原来价格下限
            int engineFactoryPricePL = aEngineFactoryDynamic.getEngineFactoryPricePL();
            // 主机厂原来价格上限
            Integer engineFactoryPricePU = aEngineFactoryDynamic.getEngineFactoryPricePU();
            // 主机厂原来平均价
            double initAvgEngineFactoryToMarketPrice = (engineFactoryPricePL + engineFactoryPricePU) / 2.0;

            // 主机厂修改服务质量-售价-产能
            if (engineFactoryCapacityUtilization >=0.8) {
                // 利用率为1(供不应求)
                if (initAvgEngineFactoryToMarketPrice >= avgFinalMarketPrice) {
                    // 初始价格的平均价 >= 所有成交价格的平均值
                    // TODO 测试的时候看看动态数据都是否更新
                    // 调整产能
                    engineFactoryCapacity = (int) Math.round(engineFactoryCapacity * 1.3);
                    // 更新下一阶段的产能
                    aEngineFactoryDynamic.setEngineFactoryCapacityM(engineFactoryCapacity);
                } else {
                    // 初始价格的平均价 < 所有成交价格的平均值
                    // 调整价格区间并更新
                    if (avgFinalMarketPrice != 0) {
                        if (engineFactoryPricePL < avgFinalMarketPrice && avgFinalMarketPrice < engineFactoryPricePU) {
                            aEngineFactoryDynamic.setEngineFactoryPricePL(RandomUtils.nextInt(engineFactoryPricePL, (int) avgFinalMarketPrice + 1));
                            aEngineFactoryDynamic.setEngineFactoryPricePU(RandomUtils.nextInt((int) avgFinalMarketPrice, engineFactoryPricePU + 1));
                        } else {
                            aEngineFactoryDynamic.setEngineFactoryPricePL((int) (avgFinalMarketPrice / 2));
                            aEngineFactoryDynamic.setEngineFactoryPricePU((int) (avgFinalMarketPrice * 1.5));
                        }
                    } else {
                        log.error("avgFinalMarketPrice == 0");
                    }
                }
            } else {
                // 利用率 < 1(供过于求)
                if (initAvgEngineFactoryToMarketPrice >= avgFinalMarketPrice) {
                    // 初始价格的平均价 >= 所有成交价格的平均值
                    if (avgFinalMarketPrice != 0) {
                        if (engineFactoryPricePL < avgFinalMarketPrice) {
                            aEngineFactoryDynamic.setEngineFactoryPricePL(RandomUtils.nextInt(engineFactoryPricePL, (int) avgFinalMarketPrice + 1));
                            aEngineFactoryDynamic.setEngineFactoryPricePU(RandomUtils.nextInt((int) avgFinalMarketPrice, engineFactoryPricePU + 1));
                        } else {
                            aEngineFactoryDynamic.setEngineFactoryPricePL((int) Math.round(avgFinalMarketPrice / 2.0));
                            aEngineFactoryDynamic.setEngineFactoryPricePU((int) Math.round(avgFinalMarketPrice * 1.5));
                        }
                    }else {
                        log.error("avgFinalMarketPrice == 0");
                    }
                } else {
                    // 初始价格的平均价 < 所有成交价格的平均值
                    int engineFactoryQualityQ = aEngineFactoryDynamic.getEngineFactoryQualityQ();
                    if (engineFactoryQualityQ >= avgFinalMarketQuality) {
                        // 质量 >= 平均质量
                        // 调整产能
                        engineFactoryCapacity = (int) Math.round(engineFactoryCapacity * 0.9);
                        // 更新下一阶段的产能
                        aEngineFactoryDynamic.setEngineFactoryCapacityM(engineFactoryCapacity);
                    } else {
                        // 质量 < 平均质量
                        if (engineFactoryQualityQ < 10) {
                            engineFactoryQualityQ++;
                            aEngineFactoryDynamic.setEngineFactoryQualityQ(engineFactoryQualityQ);
                            // 更新总资产
                            aEngineFactoryDynamic.setEngineFactoryTotalAssetsP((int) Math.round(aEngineFactoryDynamic.getEngineFactoryTotalAssetsP() * 0.9));
                        }
                    }
                }
            }
        }
    }

    /**
     * 计算供应商总资产并更新
     *
     * @param listSupplierDynamics 供应商的动态数据集合(实际被更新的)
     * @param mapSupplierProfitSum 供应商与主机厂交易后的利润
     */
    private void calAndSetSupplierTotalAssets(List<TbSupplierDynamic> listSupplierDynamics, Map<String, Integer> mapSupplierProfitSum) {
        for (TbSupplierDynamic aSupplierDynamic : listSupplierDynamics) {
            String supplierId = aSupplierDynamic.getSupplierId();
            // 开始总资产
            int initSupplierTotalAssets = aSupplierDynamic.getSupplierTotalAssetsP();
            // 利润和
            int supplierProfitSum = 0;
            if (mapSupplierProfitSum.containsKey(supplierId)) {
                supplierProfitSum = mapSupplierProfitSum.get(supplierId);
            }
            // 固定成本
            int supplierFixedCost = SupplierEnum.supplierFixedCost;
            int supplierTotalAsserts = initSupplierTotalAssets + supplierProfitSum - supplierFixedCost;
            aSupplierDynamic.setSupplierTotalAssetsP(supplierTotalAsserts);
        }
    }

    /**
     * 计算所有主机厂的总资产
     *
     * @param listEngineFactoryDynamic                 所有主机厂的动态数据集合(实际被更新的)
     * @param mapEngineFactoryProfitSum                主机厂与供应商交易后的利润集合
     * @param mapEngineIdVsOrderPlus                   主机厂与市场交易的订单集合
     * @param mapEngineIdVsEngineFactoryFinalProvision 主机厂为市场交易提供的产品实际价格和卖出结果集合
     */
    private void calAndSetEngineFactoryTotalAssets(
            List<TbEngineFactoryDynamic> listEngineFactoryDynamic
            , Map<String, Integer> mapEngineFactoryProfitSum
            , HashMap<String, OrderPlus> mapEngineIdVsOrderPlus
            , HashMap<String, EngineFactoryFinalProvision> mapEngineIdVsEngineFactoryFinalProvision) {

        for (TbEngineFactoryDynamic aEngineFactoryDynamic : listEngineFactoryDynamic) {
            String engineFactoryId = aEngineFactoryDynamic.getEngineFactoryId();
            // 开始资产
            int initEngineFactoryTotalAssets = aEngineFactoryDynamic.getEngineFactoryTotalAssetsP();
            // 销售收入 = 售价 * 实际数量
            int totalSalesMoney = 0;
            // 主机厂与供应商交易的利润和
            int engineFactoryProfit = 0;

            OrderPlus orderPlus = mapEngineIdVsOrderPlus.get(engineFactoryId);
            EngineFactoryFinalProvision engineFactoryFinalProvision = mapEngineIdVsEngineFactoryFinalProvision.get(engineFactoryId);
            if (orderPlus != null && engineFactoryFinalProvision != null) {
                // 有生产产品
                int finalMarketPrice = engineFactoryFinalProvision.getFinalMarketPrice();
                int actualSaleNumber = engineFactoryFinalProvision.getActualSaleNumber();
                totalSalesMoney = finalMarketPrice * actualSaleNumber;
                // 与供应商的交易利润
                engineFactoryProfit = mapEngineFactoryProfitSum.get(engineFactoryId);
            }
            // 固定成本
            int engineFactoryFixedCost = EngineFactoryEnum.engineFactoryFixedCost;
            int engineFactoryTotalAsserts = initEngineFactoryTotalAssets + totalSalesMoney + engineFactoryProfit - engineFactoryFixedCost;

            // 更新模型中的总资产
            aEngineFactoryDynamic.setEngineFactoryTotalAssetsP(engineFactoryTotalAsserts);
        }
    }

    /**
     * 算出各主机厂与供应商之间的交易利润和,
     * 并加入到各自对应的map集合中
     * 并把主机厂和供应商的信誉度补全
     *
     * @param listOrderPlus             交易结算集合
     * @param mapEngineFactoryProfitSum 主机厂利润集合(主机厂id, 和供应商交易好后的利润), 算好后put该处
     * @param mapSupplierProfitSum      供应商利润集合(供应商id, 和主机厂交易好后的利润), 算好后put该处
     * @param mapEngineIdVsOrderPlus    存放主机厂id对应的订单, 后续处理需要
     * @param mapEngineFactoryDynamic   主机厂的动态数据map
     * @param mapSupplierDynamic        供应商的动态数据map
     */
    private void setMapEngineFactoryAndSupplierProfitSum(
            List<OrderPlus> listOrderPlus
            , Map<String, Integer> mapEngineFactoryProfitSum
            , Map<String, Integer> mapSupplierProfitSum
            , HashMap<String, OrderPlus> mapEngineIdVsOrderPlus
            , Map<String, TbEngineFactoryDynamic> mapEngineFactoryDynamic
            , Map<String, TbSupplierDynamic> mapSupplierDynamic) {

        // 暂存主机厂利润
        int tmpEngineFactoryProfit = 0;
        // 暂存供应商利润
        int tmpSupplierProfit = 0;
        for (OrderPlus orderPlus : listOrderPlus) {
            String engineFactoryId = orderPlus.getEngineFactoryId();
            TbEngineFactoryDynamic engineFactoryDynamic = mapEngineFactoryDynamic.get(engineFactoryId);
            String supplierId = orderPlus.getSupplierId();
            TbSupplierDynamic supplierDynamic = mapSupplierDynamic.get(supplierId);

            // 更新主机厂和供应方的信誉度
            engineFactoryDynamic.setEngineFactoryCreditH(orderPlus.getEngineFactoryNewCredit());
            supplierDynamic.setSupplierCreditA(orderPlus.getSupplierNewCredit());

            mapEngineIdVsOrderPlus.put(engineFactoryId, orderPlus);
            // 主机厂与供应商交易后的利润
            if (mapEngineFactoryProfitSum.containsKey(engineFactoryId)) {
                tmpEngineFactoryProfit = mapEngineFactoryProfitSum.get(engineFactoryId);
            } else {
                tmpEngineFactoryProfit = 0;
            }
            tmpEngineFactoryProfit += orderPlus.getEngineFactoryProfit();
            mapEngineFactoryProfitSum.put(engineFactoryId, tmpEngineFactoryProfit);
            // 供应商与主机厂交易后的利润
            if (mapSupplierProfitSum.containsKey(supplierId)) {
                tmpSupplierProfit = mapSupplierProfitSum.get(supplierId);
            } else {
                tmpSupplierProfit = 0;
            }
            tmpSupplierProfit += orderPlus.getSupplierProfit();
            mapSupplierProfitSum.put(supplierId, tmpSupplierProfit);
        }
    }

    /**
     * 生成新的坐标
     *
     * @param arrPosition 信誉度最高的主机厂或供应商的位置坐标
     * @param mapPosition 主机厂或供应商的位置map
     * @return 生成的新坐标
     */
    private double[] genNewPosition(double[] arrPosition, Map<Double, Double> mapPosition) {
        double x = arrPosition[0];
        double y = arrPosition[1];
        double xLow = x - 2.5 > 0 ? x - 2.5 : 0;
        double xHigh = x + 2.5 < 20 ? x + 2.5 : 20;
        double yLow = y - 2.5 > 0 ? y - 2.5 : 0;
        double yHigh = y + 2.5 < 20 ? y + 2.5 : 20;
        while (true) {
            double newX = RandomUtils.nextDouble(xLow, xHigh);
            double newY = RandomUtils.nextDouble(yLow, yHigh);
            // 强行一位小数
            x = (int) (newX * 10) / 10d;
            y = (int) (newY * 10) / 10d;
            Double valueY = mapPosition.get(x);
            if (valueY == null) {
                // 没有x的key, 就是ok的
                mapPosition.put(x, y);
                return new double[]{x, y};
            } else {
                // 有相同的x, 看看y一不一样
                if (y != valueY) {
                    mapPosition.put(x, y);
                    return new double[]{x, y};
                }
            }

        }
    }

//--------------------------生成最终的交货结果-----------------------------

    /**
     * 生成最终的交货结果
     *
     * @param cycleTimes    实验次数
     * @param listOrderPlus 产品订单
     * @return 最终交货结果集合
     */
    @Override
    public List<EngineFactoryFinalProvision> getListEngineFactoryFinalProvision(int experimentsNumber, int cycleTimes, List<OrderPlus> listOrderPlus) {

        List<EngineFactoryFinalProvision> listEngineFactoryFinalProvision = new ArrayList<>();

        EngineFactoryFinalProvision engineFactoryFinalProvision = null;

        // tmp实际成交数量
        int actualProductNumber = 0;
        // tmp实际价格
        int actualPrice = 0;
        int tmpPrice = 0;
        // tmp实际质量
        int actualQuality = 0;

        // 当前阶段市场初始需求
//        int initMarketNeedNumber = calInitMarketNeedNumber(cycleTimes);
        int initMarketNeedNumber = demandForecastMapper.selectByPrimaryKey(cycleTimes).getTrueDemandForecast();

        int restMarketNeedNumber = initMarketNeedNumber;
        for (int i = 0; i < listOrderPlus.size(); i++) {
            OrderPlus orderPlus = listOrderPlus.get(i);
            int tmp = i % 5;
            if (tmp == 0) {
                // 实例化
                engineFactoryFinalProvision = new EngineFactoryFinalProvision();
                // 实验次数
                engineFactoryFinalProvision.setExperimentsNumber(experimentsNumber);
                // 循环次数
                engineFactoryFinalProvision.setCycleTimes(cycleTimes);
                // 主机厂Id
                engineFactoryFinalProvision.setEngineFactoryId(orderPlus.getEngineFactoryId());
                // 实际数量
                actualProductNumber = orderPlus.getSupplierActualNumberM();
                // 最终质量
                actualQuality = 0;
                // 最终面向市场价格
                actualPrice = RandomUtils.nextInt(orderPlus.getEngineFactoryToServiceOfferPriceLow(), orderPlus.getEngineFactoryToServiceOfferPriceUpper() + 1);
//                int[] engineFactory2ServiceOfferPrice = orderPlus.getEngineFactoryToServiceOfferPrice();
//                actualPrice = RandomUtils.nextInt(engineFactory2ServiceOfferPrice[0], engineFactory2ServiceOfferPrice[1] + 1);
                tmpPrice = orderPlus.getSupplierActualPriceP();

            }

            // 最终产品数量
            actualProductNumber = Math.min(orderPlus.getSupplierActualNumberM(), actualProductNumber);
            // 最终面向市场价格
            tmpPrice += orderPlus.getSupplierActualPriceP();
            // 最终质量
            actualQuality += orderPlus.getSupplierActualQualityQs();

            if (tmp == 4) {
                // 最终产品数量
                engineFactoryFinalProvision.setFinalProductNumber(actualProductNumber);
                // 最终面向市场价格
                tmpPrice = (int) (tmpPrice * CalculationEnum.saleProductsCp);
                actualPrice = Math.max(actualPrice, tmpPrice);
                engineFactoryFinalProvision.setFinalMarketPrice(actualPrice);

                // 最终质量
                actualQuality = actualQuality / 5;
                engineFactoryFinalProvision.setFinalMarketQuality(actualQuality);
                // 市场需求量
                engineFactoryFinalProvision.setMarketNeedNumber(initMarketNeedNumber);

                // 最终卖出量(实际销售额)
                int actualSaleNumber;
                if (restMarketNeedNumber > actualProductNumber) {
                    actualSaleNumber = Math.min(actualProductNumber, restMarketNeedNumber);
                    restMarketNeedNumber -= actualSaleNumber;
                } else {
                    actualSaleNumber = 0;
                }

                engineFactoryFinalProvision.setActualSaleNumber(actualSaleNumber);

                // 加入返回值数组中
                listEngineFactoryFinalProvision.add(engineFactoryFinalProvision);
            }
        }

        engineFactoryFinalProvisionMapper.insertList(listEngineFactoryFinalProvision);
        return listEngineFactoryFinalProvision;
    }

    /**
     * 计算市场需求
     *
     * @param cycleTimes 循环次数
     * @return 市场需求量
     *
     */
    public int calInitMarketNeedNumber(int cycleTimes) {
        int k1 = EngineFactoryEnum.engineFactoryDemandForecastInitK1;
        double k2 = EngineFactoryEnum.engineFactoryDemandForecastInitK2;
        int k1Step = EngineFactoryEnum.engineFactoryDemandForecastK1Step;
        double k2Step = EngineFactoryEnum.engineFactoryDemandForecastK2Step;
        double cm = CalculationEnum.saleProductsInitCm;
        k1 = k1 + (cycleTimes - 1) * k1Step;
        k2 = k2 + (cycleTimes - 1) * k2Step;
        cm = cm + (cycleTimes - 1) * 0.2;

        int pa = RandomUtils.nextInt(EngineFactoryEnum.engineFactoryInitPriceLow, EngineFactoryEnum.engineFactoryInitPriceUpper + 1);
        int qa = RandomUtils.nextInt(EngineFactoryEnum.engineFactoryInitQualityLow, EngineFactoryEnum.engineFactoryInitQualityUpper + 1);
        int res = (int) Math.round((k1 - k2 * pa / qa) * cm);
        return res > 0 ? res : 0;
    }
}
