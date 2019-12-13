package cn.edu.zju.kpaperproject.service.impl;

import cn.edu.zju.kpaperproject.dto.EngineFactoryFinalProvision;
import cn.edu.zju.kpaperproject.dto.OrderPlus;
import cn.edu.zju.kpaperproject.dto.TransactionContract;
import cn.edu.zju.kpaperproject.enums.CalculationEnum;
import cn.edu.zju.kpaperproject.enums.EngineFactoryEnum;
import cn.edu.zju.kpaperproject.enums.SupplierEnum;
import cn.edu.zju.kpaperproject.pojo.TbEngineFactoryDynamic;
import cn.edu.zju.kpaperproject.pojo.TbSupplierDynamic;
import cn.edu.zju.kpaperproject.service.BeforeNextTask;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
@Service
public class BeforeNextTaskImpl implements BeforeNextTask {

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
     * @param listEngineFactoryFinalProvisions 最终交货结果集合
     * @param listOrderPlus                    实际交易结果集合
     * @param listEngineFactoryDynamic         所有存活主机厂动态数据的集合
     * @param listSupplierDynamics             所有存活服务商动态数据集合
     */
    @Override
    public void beforeNextTask(
            List<EngineFactoryFinalProvision> listEngineFactoryFinalProvisions
            , List<OrderPlus> listOrderPlus
            , ArrayList<TransactionContract> listTransactionContract
            , List<TbEngineFactoryDynamic> listEngineFactoryDynamic
            , List<TbSupplierDynamic> listSupplierDynamics) {

        // 暂存主机厂利润和
        Map<String, Integer> mapEngineFactoryProfitSum = new HashMap<>(100);

        // 暂存供应方利润和
        Map<String, Integer> mapSupplierProfitSum = new HashMap<>(100);

        // 用来存id, 看是否有交易
        HashMap<String, OrderPlus> mapEngineIdVsOrderPlus = new HashMap<>(100);

        for (OrderPlus orderPlus : listOrderPlus) {
            String engineFactoryId = orderPlus.getEngineFactoryId();
            String supplierId = orderPlus.getSupplierId();
            mapEngineIdVsOrderPlus.put(engineFactoryId, orderPlus);
            // 主机厂与供应商交易后的利润
            int tmpEngineFactoryProfit = mapEngineFactoryProfitSum.get(engineFactoryId);
            tmpEngineFactoryProfit += orderPlus.getEngineFactoryProfit();
            mapEngineFactoryProfitSum.put(engineFactoryId, tmpEngineFactoryProfit);
            // 供应商与主机厂交易后的利润
            int tmpSupplierProfit = mapSupplierProfitSum.get(supplierId);
            tmpSupplierProfit += orderPlus.getSupplierProfit();
            mapSupplierProfitSum.put(supplierId, tmpEngineFactoryProfit);


        }

        // 用来存成交价格
        int sumFinalMarketPrice = 0;
        // 用来记录产品的成交质量
        int sumFinalMarketQuality = 0;

        // 主机厂总资产计算
        // 主机厂与市场交易Map
        HashMap<String, EngineFactoryFinalProvision> mapEngineIdVsEngineFactoryFinalProvision = new HashMap<>(100);
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

        for (TbEngineFactoryDynamic aEngineFactoryDynamic : listEngineFactoryDynamic) {
            // 计算所有主机厂与供应商的总资产
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

        // 供应商总资产计算并更新
        for (TbSupplierDynamic aSupplierDynamic : listSupplierDynamics) {
            String supplierId = aSupplierDynamic.getSupplierId();
            // 开始总资产
            int initSupplierTotalAssets = aSupplierDynamic.getSupplierTotalAssetsP();
            // 利润和
            int supplierProfitSum = mapSupplierProfitSum.get(supplierId);
            // 固定成本
            int supplierFixedCost = SupplierEnum.supplierFixedCost;
            int supplierTotalAsserts = initSupplierTotalAssets + supplierProfitSum - supplierFixedCost;
            aSupplierDynamic.setSupplierTotalAssetsP(supplierTotalAsserts);
        }

        // TODO 先分开循环吧, 之后看看能不能合并
        // 计算并更新所有主机厂的产能利用率
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
            if (engineFactoryCapacityUtilization == 1) {
                // 利用率为1(供不应求)
                if (initAvgEngineFactoryToMarketPrice >= avgFinalMarketPrice) {
                    // 初始价格的平均价 >= 所有成交价格的平均值
                    // TODO 测试的时候看看动态数据都是否更新
                    // 调整产能
                    engineFactoryCapacity = (int) Math.round(engineFactoryCapacity * 1.1);
                    // 更新下一阶段的产能
                    aEngineFactoryDynamic.setEngineFactoryCapacityM(engineFactoryCapacity);
                } else {
                    // 初始价格的平均价 < 所有成交价格的平均值
                    // 调整价格区间并更新
                    aEngineFactoryDynamic.setEngineFactoryPricePL(RandomUtils.nextInt(engineFactoryPricePL, (int) avgFinalMarketPrice + 1));
                    aEngineFactoryDynamic.setEngineFactoryPricePU(RandomUtils.nextInt((int) avgFinalMarketPrice, engineFactoryPricePU + 1));
                }
            } else {
                // 利用率 < 1(供过于求)
                if (initAvgEngineFactoryToMarketPrice >= avgFinalMarketPrice) {
                    // 初始价格的平均价 >= 所有成交价格的平均值
                    aEngineFactoryDynamic.setEngineFactoryPricePL(RandomUtils.nextInt(engineFactoryPricePL, (int) avgFinalMarketPrice + 1));
                    aEngineFactoryDynamic.setEngineFactoryPricePU(RandomUtils.nextInt((int) avgFinalMarketPrice, engineFactoryPricePU + 1));
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

        // 计算供应商的产能利用率
        for (TbSupplierDynamic aSupplierDynamic : listSupplierDynamics) {
            // 阶段开始时初始产能
            int initSupplierCapacity = aSupplierDynamic.getSupplierCapacityM();
            // 计算利用率并更新
            // 某类供应总需求
            int sumSupplierOrderNumber;
            // 某类平均需求数量
            double avgSupplierOrderNumber = 0;
            // 某类供应质量
            int avgSupplierQuality = 0;
            int supplierType = aSupplierDynamic.getSupplierType();
            switch (supplierType) {
                case 210:
                    sumSupplierOrderNumber = sumArrSupplierOrderNumber[0];
                    avgSupplierOrderNumber = avgArrSupplierOrderNumber[0];
                    avgSupplierQuality = avgArrSupplierQuality[0];
                    break;
                case 220:
                    sumSupplierOrderNumber = sumArrSupplierOrderNumber[1];
                    avgSupplierOrderNumber = avgArrSupplierOrderNumber[1];
                    avgSupplierQuality = avgArrSupplierQuality[1];
                    break;
                case 230:
                    sumSupplierOrderNumber = sumArrSupplierOrderNumber[2];
                    avgSupplierOrderNumber = avgArrSupplierOrderNumber[2];
                    avgSupplierQuality = avgArrSupplierQuality[2];
                    break;
                case 240:
                    sumSupplierOrderNumber = sumArrSupplierOrderNumber[3];
                    avgSupplierOrderNumber = avgArrSupplierOrderNumber[3];
                    avgSupplierQuality = avgArrSupplierQuality[3];
                    break;
                case 250:
                    sumSupplierOrderNumber = sumArrSupplierOrderNumber[4];
                    avgSupplierOrderNumber = avgArrSupplierOrderNumber[4];
                    avgSupplierQuality = avgArrSupplierQuality[4];
                    break;
                default:
                    throw new RuntimeException("no such type");
            }


            // 计算产能利用率
            double supplierCapacityUtilization = sumSupplierOrderNumber * 1D / aSupplierDynamic.getSupplierCapacityM();
            // 更新
            aSupplierDynamic.setSupplierCapacityUtilization(supplierCapacityUtilization);

            // 主机产能
            int supplierCapacity = aSupplierDynamic.getSupplierCapacityM();

            // 供应商原来价格下限
            int supplierPricePL = aSupplierDynamic.getSupplierPricePL();
            // 供应商原来价格上限
            Integer supplierPricePU = aSupplierDynamic.getSupplierPricePU();
            // 供应商原来平均价
            double initAvgSupplierPrice = (supplierPricePL + supplierPricePU) / 2.0;

            if (supplierCapacityUtilization == 1) {
                // 产品成交的平均质量
                // 利用率为1(供不应求)
                if (initAvgSupplierPrice >= avgSupplierOrderNumber) {
                    // 初始价格的平均价 >= 所有成交价格的平均值
                    // TODO 测试的时候看看动态数据都是否更新
                    // 调整产能
                    supplierCapacity = (int) Math.round(supplierCapacity * 1.1);
                    // 更新下一阶段的产能
                    aSupplierDynamic.setSupplierCapacityM(supplierCapacity);
                } else {
                    // 初始价格的平均价 < 所有成交价格的平均值
                    // 调整价格区间并更新
                    aSupplierDynamic.setSupplierPricePL(RandomUtils.nextInt(supplierPricePL, (int) initAvgSupplierPrice + 1));
                    aSupplierDynamic.setSupplierPricePU(RandomUtils.nextInt((int) initAvgSupplierPrice, supplierPricePU + 1));
                }
            } else {
                // 利用率 < 1(供过于求)
                if (initAvgSupplierPrice >= avgFinalMarketPrice) {
                    // 初始价格的平均价 >= 所有成交价格的平均值
                    aSupplierDynamic.setSupplierPricePL(RandomUtils.nextInt(supplierPricePL, (int) initAvgSupplierPrice + 1));
                    aSupplierDynamic.setSupplierPricePU(RandomUtils.nextInt((int) initAvgSupplierPrice, supplierPricePU + 1));
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
            }
        }


        // 企业进入与退出
        // 双方信誉度归一化
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
    public List<EngineFactoryFinalProvision> getListEngineFactoryFinalProvision(int cycleTimes, List<OrderPlus> listOrderPlus) {

        List<EngineFactoryFinalProvision> listRes = new ArrayList<>();

        EngineFactoryFinalProvision engineFactoryFinalProvision = null;

        // tmp实际成交数量
        int actualProductNumber = 0;
        // tmp实际价格
        int actualPrice = 0;
        int tmpPrice = 0;
        // tmp实际质量
        int actualQuality = 0;

        // 市场初始需求
        int initMarketNeedNumber = calInitMarketNeedNumber(cycleTimes);
        int restMarketNeedNumber = initMarketNeedNumber;
        for (int i = 0; i < listOrderPlus.size(); i++) {
            OrderPlus orderPlus = listOrderPlus.get(i);


            int tmp = i % 5;
            if (tmp == 0) {
                // 实例化
                engineFactoryFinalProvision = new EngineFactoryFinalProvision();
                // 主机厂Id
                engineFactoryFinalProvision.setEngineFactoryId(orderPlus.getEngineFactoryId());
                // 实际数量
                actualProductNumber = orderPlus.getSupplierActualNumberM();
                // 最终质量
                actualQuality = 0;
                // 最终面向市场价格
                int[] engineFactory2ServiceOfferPrice = orderPlus.getEngineFactory2ServiceOfferPrice();
                actualPrice = RandomUtils.nextInt(engineFactory2ServiceOfferPrice[0], engineFactory2ServiceOfferPrice[1] + 1);
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
                actualPrice = Math.min(actualPrice, tmpPrice);
                engineFactoryFinalProvision.setFinalMarketPrice(actualPrice);

                // 最终质量
                actualQuality = actualQuality / 5;
                engineFactoryFinalProvision.setFinalMarketQuality(actualQuality);
                // 市场需求量
                engineFactoryFinalProvision.setMarketNeedNumber(initMarketNeedNumber);

                // 最终卖出量(实际销售额)
                int actualSaleNumber = Math.min(actualProductNumber, restMarketNeedNumber);
                restMarketNeedNumber -= actualSaleNumber;
                engineFactoryFinalProvision.setActualSaleNumber(actualSaleNumber);

                // 加入返回值数组中
                listRes.add(engineFactoryFinalProvision);
            }
        }
        return listRes;
    }

    /**
     * 计算市场需求
     *
     * @param cycleTimes 循环次数
     * @return 市场需求量
     */
    public int calInitMarketNeedNumber(int cycleTimes) {
        int k1 = EngineFactoryEnum.engineFactoryDemandForecastInitK1;
        double k2 = EngineFactoryEnum.engineFactoryDemandForecastInitK2;
        int k1Step = EngineFactoryEnum.engineFactoryDemandForecastK1Step;
        double k2Step = EngineFactoryEnum.engineFactoryDemandForecastK2Step;
        int cm = CalculationEnum.saleProductsInitCm;
        k1 = k1 + cycleTimes * k1Step;
        k2 = k2 + cycleTimes * k2Step;
        cm = cm + cycleTimes;

        int pa = RandomUtils.nextInt(EngineFactoryEnum.engineFactoryInitPriceLow, EngineFactoryEnum.engineFactoryInitPriceUpper + 1);
        int qa = RandomUtils.nextInt(EngineFactoryEnum.engineFactoryInitQualityLow, EngineFactoryEnum.engineFactoryInitQualityUpper + 1);
        return (int) Math.round((k1 - k2 * pa / qa) * cm);
    }
}
