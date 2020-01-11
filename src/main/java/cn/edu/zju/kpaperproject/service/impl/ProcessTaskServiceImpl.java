package cn.edu.zju.kpaperproject.service.impl;

import cn.edu.zju.kpaperproject.dto.EngineFactoryManufacturingTask;
import cn.edu.zju.kpaperproject.dto.SupplierTask;
import cn.edu.zju.kpaperproject.dto.TransactionContract;
import cn.edu.zju.kpaperproject.enums.CalculationEnum;
import cn.edu.zju.kpaperproject.enums.NumberEnum;
import cn.edu.zju.kpaperproject.mapper.OrderPlusMapper;
import cn.edu.zju.kpaperproject.pojo.OrderPlus;
import cn.edu.zju.kpaperproject.pojo.TbRelationMatrix;
import cn.edu.zju.kpaperproject.service.ProcessTaskService;
import cn.edu.zju.kpaperproject.utils.CalculationUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static cn.edu.zju.kpaperproject.enums.Config.TRANSACTION_SETTLEMENT_GUARANTEED;

/**
 * 任务处理相关的服务
 *
 * @author RichardLee
 * @version v1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ProcessTaskServiceImpl implements ProcessTaskService {

    @Autowired
    private OrderPlusMapper orderPlusMapper;

    /**
     * 交易结算
     *
     * @param experimentsNumber                          实验次数
     * @param cycleTimes                                 循环次数
     * @param listTransactionContracts                   交易契约集合
     * @param mapRelationshipMatrix                      关系矩阵1
     * @param mapRelationshipMatrix2WithTbRelationMatrix 关系矩阵2
     * @return 实际交易结果集合
     */
    @Override
    public List<OrderPlus> getTransactionSettlement(
            int experimentsNumber
            , int cycleTimes
            , ArrayList<TransactionContract> listTransactionContracts
            , Map<String, Double> mapRelationshipMatrix
            , Map<String, TbRelationMatrix> mapRelationshipMatrix2WithTbRelationMatrix) {

        // 返回值的list
        List<OrderPlus> listOrderPlus = new ArrayList<>();

        // 主机厂和供应商的履约概率
        double engineFactoryPerformanceProbability;
        double supplierPerformanceProbability;
        // 信誉度计算相关的map
        // 主机厂的
        Map<String, List<OrderPlus>> mapEngineFactoryCredit = new HashMap<>(100);
        // 供应商的
        Map<String, List<OrderPlus>> mapSupplierCredit = new HashMap<>(100);

        for (TransactionContract aTransactionContract : listTransactionContracts) {
            // 每次循环是每个交易契约
            OrderPlus orderPlus = new OrderPlus();
            // 双方id
            orderPlus.setEngineFactoryId(aTransactionContract.getEngineFactoryId());
            orderPlus.setSupplierId(aTransactionContract.getSupplierId());
            // 主机厂初始期望价格
//            orderPlus.setEngineFactoryToServiceOfferPrice(aTransactionContract.getEngineFactory2ServiceOfferPrice());
            orderPlus.setEngineFactoryToServiceOfferPriceLow(aTransactionContract.getEngineFactory2ServiceOfferPrice()[0]);
            orderPlus.setEngineFactoryToServiceOfferPriceUpper(aTransactionContract.getEngineFactory2ServiceOfferPrice()[1]);

            // 实验次数与循环次数
            orderPlus.setExperimentsNumber(experimentsNumber);
            orderPlus.setCycleTimes(cycleTimes);
            // 计算双方履约概率
            double[] performanceProbability = getPerformanceProbability(aTransactionContract, mapRelationshipMatrix);
            engineFactoryPerformanceProbability = performanceProbability[0];
            supplierPerformanceProbability = performanceProbability[1];
            orderPlus.setEngineToSupplierAp(engineFactoryPerformanceProbability);
            orderPlus.setSupplierEngineToAp(supplierPerformanceProbability);

            // 计算是否履约
            boolean[] whetherPerformContract = getWhetherPerformContract(engineFactoryPerformanceProbability, supplierPerformanceProbability);
            orderPlus.setEngineWhetherPerformContract(whetherPerformContract[0]);
            orderPlus.setSupplierWhetherPerformContract(whetherPerformContract[1]);

            // 实际价格
            orderPlus.setSupplierActualPriceP(aTransactionContract.getOrderPrice());
            // 实际质量
            orderPlus.setSupplierActualQualityQs(aTransactionContract.getOrderQuality());
            // 计算实际交易数量
            int actualTransactionsNumber = getActualTransactionsNumber(aTransactionContract.getEngineFactoryNeedServiceNumber(), performanceProbability, whetherPerformContract);
            orderPlus.setSupplierActualNumberM(actualTransactionsNumber);

            // 双方评分
            int[] evaluationScore = getEvaluationScore(whetherPerformContract);
            orderPlus.setEngineFactoryToSupplierScore(evaluationScore[1]);
            orderPlus.setSupplierToEngineFactoryScore(evaluationScore[0]);

            // 计算新的关系强度
            double newRelationshipStrength = getNewRelationshipStrength(aTransactionContract, whetherPerformContract, evaluationScore, mapRelationshipMatrix2WithTbRelationMatrix);
            orderPlus.setRelationshipStrength(newRelationshipStrength);
            orderPlus.setRelationshipStrength(newRelationshipStrength);

            // 计算利润
            int[] profit = getProfit(aTransactionContract, whetherPerformContract, actualTransactionsNumber);
            orderPlus.setEngineFactoryProfit(profit[0]);
            orderPlus.setSupplierProfit(profit[1]);

            // 初始信誉度
            orderPlus.setEngineFactoryInitCredit(aTransactionContract.getEngineFactoryCredit());
            orderPlus.setSupplierInitCredit(aTransactionContract.getSupplierCredit());
            // 计算交易后的信誉度, 放在map里先
            addMapForCredit(orderPlus, mapEngineFactoryCredit, mapSupplierCredit);

            listOrderPlus.add(orderPlus);
        }
        // 计算信誉度
        for (OrderPlus aOrderPlus : listOrderPlus) {
            String engineFactoryId = aOrderPlus.getEngineFactoryId();
            String supplierId = aOrderPlus.getSupplierId();
            // 主机厂id 对应的listOrder(也就是说这个主机厂与5个供应商相关的订单)
            List<OrderPlus> listEngineFactoryMatchSupplier = mapEngineFactoryCredit.get(engineFactoryId);
            // 供应商id 对应的listOrder(供应商对那家主机厂提供服务代订单)
            List<OrderPlus> listSupplierMatchEngineFactory = mapSupplierCredit.get(supplierId);

            // TODO 这里应该是可以消除重复计算的, 后续再优化
            // 补全主机厂新的的信誉度
            double engineFactoryNewCredit = getNewCredit(listEngineFactoryMatchSupplier, "engine");
            aOrderPlus.setEngineFactoryNewCredit(engineFactoryNewCredit);
            // 补全供应商新的信誉度
            double supplierNewCredit = getNewCredit(listSupplierMatchEngineFactory, "supplier");
            aOrderPlus.setSupplierNewCredit(supplierNewCredit);
        }

        // # 把orderPlus存入数据库
        orderPlusMapper.insertList(listOrderPlus);
//        for (OrderPlus orderPlus : listOrderPlus) {
//            orderPlusMapper.insertSelective(orderPlus);
//        }
        return listOrderPlus;
    }

    /**
     * 计算主机厂或者供应商的信誉度
     *
     * @param listMatches 主机厂对应的所有供应商集合 或 供应商对应的所有主机厂集合
     * @param type        engine supplier
     * @return
     */
    private double getNewCredit(List<OrderPlus> listMatches, String type) {
        double initCredit;
        double sum = 0D;
        switch (type) {
            case "engine":
                initCredit = listMatches.get(0).getEngineFactoryInitCredit();
                for (OrderPlus orderPlus : listMatches) {
                    // 主机厂的履约情况
                    boolean supplierWhetherPerformContract = orderPlus.getEngineWhetherPerformContract();
                    // 供应商的评分
                    int supplierToEngineFactoryScore = orderPlus.getSupplierToEngineFactoryScore();
                    // 供应商的信誉度
                    double initSupplierCredit = orderPlus.getSupplierInitCredit();
                    if (supplierWhetherPerformContract) {
                        // 履约
                        sum += supplierToEngineFactoryScore * initSupplierCredit;
                    } else {
                        // 违约
                        sum -= supplierToEngineFactoryScore * initSupplierCredit;
                    }
                }
                break;
            case "supplier":
                initCredit = listMatches.get(0).getSupplierInitCredit();
                for (OrderPlus orderPlus : listMatches) {
                    // 供应商的履约情况
                    boolean supplierWhetherPerformContract = orderPlus.getSupplierWhetherPerformContract();
                    // 主机厂的评分
                    int engineFactoryToSupplierScore = orderPlus.getEngineFactoryToSupplierScore();
                    // 主机厂的信誉度
                    double initEngineFactoryCredit = orderPlus.getEngineFactoryInitCredit();
                    if (supplierWhetherPerformContract) {
                        // 履约
                        sum += engineFactoryToSupplierScore * initEngineFactoryCredit;
                    } else {
                        // 违约
                        sum -= engineFactoryToSupplierScore * initEngineFactoryCredit;
                    }
                }
                break;
            default:
                throw new RuntimeException("no such type");
        }
        return initCredit + sum / (10 * listMatches.size());
    }

    /**
     * 把信誉度计算相关的map放进来
     *
     * @param orderPlus              成交结束相关的模型
     * @param mapEngineFactoryCredit key: 主机厂id value: 匹配上的供应商集合
     * @param mapSupplierCredit      key: 供应商id value: 匹配上的主机厂
     */
    private void addMapForCredit(OrderPlus orderPlus, Map<String, List<OrderPlus>> mapEngineFactoryCredit, Map<String, List<OrderPlus>> mapSupplierCredit) {
        // 双方id
        String engineFactoryId = orderPlus.getEngineFactoryId();
        String supplierId = orderPlus.getSupplierId();
        List<OrderPlus> listEngineFactoryMatchSupplier = mapEngineFactoryCredit.get(engineFactoryId);
        List<OrderPlus> listSupplierMatchEngineFactory = mapEngineFactoryCredit.get(supplierId);
        if (listEngineFactoryMatchSupplier == null) {
            listEngineFactoryMatchSupplier = new ArrayList<>();
        }
        if (listSupplierMatchEngineFactory == null) {
            listSupplierMatchEngineFactory = new ArrayList<>();
        }
        listEngineFactoryMatchSupplier.add(orderPlus);
        listSupplierMatchEngineFactory.add(orderPlus);
        // TODO 稍微可以优化一下下
        mapEngineFactoryCredit.put(engineFactoryId, listEngineFactoryMatchSupplier);
        mapSupplierCredit.put(supplierId, listSupplierMatchEngineFactory);
    }

    /**
     * 计算双方利润
     *
     * @param transactionContract      交易契约
     * @param whetherPerformContract   是否履约数组
     * @param actualTransactionsNumber 实际交易数量
     * @return 0: 主机厂利润, 1: 供应商利润
     */
    private int[] getProfit(TransactionContract transactionContract, boolean[] whetherPerformContract, int actualTransactionsNumber) {
        boolean engineIsPerformContract = whetherPerformContract[0];
        boolean supplierIsPerformContract = whetherPerformContract[1];
        // 公式里绝对值的部分(只有绝对值)
        int absJKI = 0;
        int absIJK = 0;
        if ((engineIsPerformContract && supplierIsPerformContract) || (!engineIsPerformContract && !supplierIsPerformContract)) {
            // 1 1 或 0 0
            // 使用默认值
        } else if (engineIsPerformContract) {
            // 1 0
            absJKI = 2;
//            absJKI = 1;
            absIJK = -2;
//            absIJK = -1;
        } else {
            // 0 1
            absJKI = -2;
//            absJKI = -1;
            absIJK = 2;
//            absIJK = 1;
        }
        // 合同价
        int orderPrice = transactionContract.getOrderPrice();
        // 合同数量
        int engineFactoryNeedServiceNumber = transactionContract.getEngineFactoryNeedServiceNumber();

        int[] res = new int[2];
        res[0] = (int) (absJKI * orderPrice * engineFactoryNeedServiceNumber + (absIJK - 1.25) * orderPrice * actualTransactionsNumber);
        // 两地距离
        int distance = (int) Math.round(CalculationUtils.calDistance(transactionContract.getEngineFactoryLocationXY(), transactionContract.getSupplierLocationXY()));
        res[1] = (int) ((absIJK * orderPrice * engineFactoryNeedServiceNumber + (1 + absJKI) * orderPrice * actualTransactionsNumber - distance * CalculationEnum.freight) * 0.1);
        return res;
    }

    /**
     * 计算交易后新的关系强度
     *
     * @param transactionContract                        交易契约
     * @param whetherPerformContract                     是否履约
     * @param evaluationScore                            双方评分
     * @param mapRelationshipMatrix2WithTbRelationMatrix 关系强度
     * @return 计算后的新的关系强度
     */
    private double getNewRelationshipStrength(TransactionContract transactionContract, boolean[] whetherPerformContract, int[] evaluationScore, Map<String, TbRelationMatrix> mapRelationshipMatrix2WithTbRelationMatrix) {

        String key = transactionContract.getEngineFactoryId() + transactionContract.getSupplierId();
        TbRelationMatrix tbRelationMatrix = mapRelationshipMatrix2WithTbRelationMatrix.get(key);
        // 初始关系强度
        double initialRelationalDegree = tbRelationMatrix.getInitialRelationalDegree();
        // 阿尔法2撇
        int relationshipStrengthA2Slash = CalculationEnum.relationshipStrengthA2Slash;
        // 历史累加中间变量
        int accumulativeTotalScore = tbRelationMatrix.getAccumulativeTotalScore();
        // 双方评分和
        int evaluationScoreSum = evaluationScore[0] + evaluationScore[1];
        // 计算累加的值
        if (whetherPerformContract[0] && whetherPerformContract[1]) {
            // 都履约为正
            accumulativeTotalScore += evaluationScoreSum;
        } else {
            // 有一个不履约就为负
            accumulativeTotalScore -= evaluationScoreSum;
        }
        // 重新设置累加的中间变量
        tbRelationMatrix.setAccumulativeTotalScore(accumulativeTotalScore);

        // 交易次数 =0?

        int transactionNumber = tbRelationMatrix.getTransactionNumber() + 1;
        tbRelationMatrix.setTransactionNumber(transactionNumber);
        // 重新计算出的关系强度
        double relationScore = initialRelationalDegree + 1D * relationshipStrengthA2Slash * accumulativeTotalScore / (20 * transactionNumber);
        tbRelationMatrix.setRelationScore(relationScore);
        return relationScore;
    }


    private int getActualTransactionsNumber(int engineFactoryNeedServiceNumber, double[] performanceProbability, boolean[] whetherPerformContract) {
        int res = engineFactoryNeedServiceNumber;
        int tmpAbs = 1;
        for (int i = 0; i < whetherPerformContract.length; i++) {
            if (!whetherPerformContract[i]) {
                // 违约
                tmpAbs *= performanceProbability[i];
            }
        }
        res = (int) Math.round((TRANSACTION_SETTLEMENT_GUARANTEED + (1 - TRANSACTION_SETTLEMENT_GUARANTEED) * tmpAbs) * engineFactoryNeedServiceNumber);
        return res;
    }

    /**
     * 计算双方互评分数
     *
     * @param whetherPerformContract 是否履约
     * @return 0: 供应商对主机厂的分数, 1: 主机厂对供应商的分数
     */
    private int[] getEvaluationScore(boolean[] whetherPerformContract) {
        int[] res = new int[2];
        for (int i = 0; i < whetherPerformContract.length; i++) {
            // i = 0 主机厂是否履约
            // i = 1 供应商是否履约
            boolean isPerformContract = whetherPerformContract[i];
            if (isPerformContract) {
                res[i] = RandomUtils.nextInt(6, 11);
            } else {
                res[i] = RandomUtils.nextInt(1, 6);
            }
        }
        return res;
    }

    /**
     * 主机厂与供应商是否履约
     * true 为履约, false 为违约
     *
     * @param engineFactoryPerformanceProbability 主机厂履约概率
     * @param supplierPerformanceProbability      供应商履约概率
     * @return 0: 主机厂是否履约, 1: 供应商是否履约
     */
    private boolean[] getWhetherPerformContract(double engineFactoryPerformanceProbability, double supplierPerformanceProbability) {
        boolean[] res = new boolean[2];
        double tmp = RandomUtils.nextDouble(0D, 1D);
        // 主机厂是否履约
        res[0] = tmp < engineFactoryPerformanceProbability;
        tmp = RandomUtils.nextDouble(0D, 1D);
        // 供应商是否履约
        res[1] = tmp < supplierPerformanceProbability;
        return res;
    }

    /**
     * 算履约概率的
     *
     * @param transactionContract   交易契约
     * @param mapRelationshipMatrix 关系矩阵
     * @return 0: 主机厂履约概率, 1: 供应商履约概率
     */
    private double[] getPerformanceProbability(TransactionContract transactionContract, Map<String, Double> mapRelationshipMatrix) {
        // key
        String key = transactionContract.getEngineFactoryId() + transactionContract.getSupplierId();
        // 信誉度
        double engineFactoryCredit = transactionContract.getEngineFactoryCredit();
        double supplierCredit = transactionContract.getSupplierCredit();
        double apLm1 = CalculationEnum.apLm1;
        double apLm2 = CalculationEnum.apLm2;
        double apLm3 = CalculationEnum.apLm3;
        double apLm4 = CalculationEnum.apLm4;
        double apLm5 = CalculationEnum.apLm5;
        double relationshipValue = mapRelationshipMatrix.get(key);
        double engineFactoryPerformanceProbability = apLm1 * engineFactoryCredit + apLm2 * relationshipValue;
//        engineFactoryPerformanceProbability = engineFactoryPerformanceProbability >= 0 ? engineFactoryPerformanceProbability : 0.4;
        engineFactoryPerformanceProbability = engineFactoryPerformanceProbability >= 0.3 ? engineFactoryPerformanceProbability : 0.3;
//        engineFactoryPerformanceProbability = engineFactoryPerformanceProbability <= 1 ? engineFactoryPerformanceProbability : 0.8;
        engineFactoryPerformanceProbability = engineFactoryPerformanceProbability <= 0.8 ? engineFactoryPerformanceProbability : 0.8;
        double supplierPerformanceProbability = apLm3 * supplierCredit + apLm4 * relationshipValue + apLm5 * RandomUtils.nextDouble(0D, 1D);
//        supplierPerformanceProbability = supplierPerformanceProbability >= 0 ? supplierPerformanceProbability : 0.4;
        supplierPerformanceProbability = supplierPerformanceProbability >= 0.3 ? supplierPerformanceProbability : 0.3;
//        supplierPerformanceProbability = supplierPerformanceProbability <= 1 ? supplierPerformanceProbability : 0.8;
        supplierPerformanceProbability = supplierPerformanceProbability <= 0.8 ? supplierPerformanceProbability : 0.8;
        return new double[]{engineFactoryPerformanceProbability, supplierPerformanceProbability};
    }


    //-----------------------------------交易契约相关------------------------------------------

    /**
     * 获取交易契约
     *
     * @param listListEngineFactoryTasks 按主机厂分的任务集合, 每个元素是一个主机厂集合(集合内元素是该主机厂的任务集)
     * @param listListSupplierTask       按任务分开后的供应商服务集合
     * @param mapRelationshipMatrix      关系强度
     * @return 匹配上的主机厂与供应商的交易契约
     */
    @Override
    public ArrayList<TransactionContract> getTransactionContracts(
            ArrayList<ArrayList<EngineFactoryManufacturingTask>> listListEngineFactoryTasks
            , ArrayList<ArrayList<SupplierTask>> listListSupplierTask
            , Map<String, Double> mapRelationshipMatrix) {

        // 中间值__list中每个map元素代表一个厂的所有任务和最终匹配上的唯一供应商
        ArrayList<LinkedHashMap<EngineFactoryManufacturingTask, SupplierTask>> listMapEngineFactoryTaskVsSupplierTask = new ArrayList<>();

        // 每个厂都经历这么几个步骤
        startEachEngineFactory:
        for (ArrayList<EngineFactoryManufacturingTask> listEngineFactoryTask : listListEngineFactoryTasks) {
            // 每个循环是一个主机厂的所有任务集合

            // LinkedHashMap<任务集合, 服务>
            // 一个主机厂一个Map
            LinkedHashMap<EngineFactoryManufacturingTask, SupplierTask> mapEngineTaskVsSupplierTask = new LinkedHashMap<>();

//            log.error("~~~~~~~~~~~~~~~~~listEngineFactoryTask~~~START~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//            log.info("getEngineFactoryId   " + listEngineFactoryTask.get(0).getEngineFactoryId());
//            log.info("engineFactoryNeedServiceNumber :  " + listEngineFactoryTask.get(0).getEngineFactoryNeedServiceNumber());

            // 匹配
            // 每个任务都进行粗, 再匹配, 精匹配(里面是剩余产能够的), 成功加入, 不成功删除
            for (EngineFactoryManufacturingTask aEngineFactoryManufacturingTask : listEngineFactoryTask) {
                // 每个循环是一个主机厂的一个任务
                // 一个任务要走完所有流程
                // 每个主机厂任务 aEngineFactoryManufacturingTask

                // # 条件
                // 任务类型相同
                int taskType = aEngineFactoryManufacturingTask.getTaskType();
                // 初始对应的服务集合
                ArrayList<SupplierTask> supplierTasks = getSupplierTasks(listListSupplierTask, taskType);


                // ## 粗匹配( 用剩余产能 )
                ArrayList<SupplierTask> listMatchingSupplierTasks = roughMatching(aEngineFactoryManufacturingTask, supplierTasks);

                // ## 再匹配
                // 粗匹配没匹配上, 就降低要求来匹配
                if (listMatchingSupplierTasks.size() == 0) {
                    // 一个都没匹配上的, 再次匹配
                    listMatchingSupplierTasks = reGenListMatchingSupplierTask(aEngineFactoryManufacturingTask, supplierTasks);

                }
                // 如果此时还是没匹配上, 那么整个厂就不匹配了
                if (listMatchingSupplierTasks == null || listMatchingSupplierTasks.size() == 0) {
                    // 回滚
                    rollbackSupplierRestCapacity(mapEngineTaskVsSupplierTask);
                    continue startEachEngineFactory;
                }
                // 能到这里的, 都是至少有一家服务匹配上
                // ## 精匹配
                // 算出唯一的任务, 剩余产能减了
                // 主机厂任务
                SupplierTask supplierTask;
                // 主机厂需要量
                int engineFactoryNeedServiceNumber;
                // 供应商剩余产能
                int supplierRestCapacity;
                // 按服务数量匹配
                if (listMatchingSupplierTasks.size() == 1) {
                    // 主机厂任务数量匹配到的服务 == 1
                    // 供应商
                    supplierTask = listMatchingSupplierTasks.get(0);
                    // 主机厂需要量
                    engineFactoryNeedServiceNumber = aEngineFactoryManufacturingTask.getEngineFactoryNeedServiceNumber();
                    // 供应商还能提供量
                    supplierRestCapacity = supplierTask.getSupplierRestCapacity();
                    // 判断供应商剩余产能
                    if (supplierRestCapacity > engineFactoryNeedServiceNumber) {
                        // 供应商的剩余产能相减
                        supplierRestCapacity = supplierRestCapacity - engineFactoryNeedServiceNumber;
                        supplierTask.setSupplierRestCapacity(supplierRestCapacity);
                        // 插入匹配map中
                        mapEngineTaskVsSupplierTask.put(aEngineFactoryManufacturingTask, supplierTask);
                    } else {
                        // 没匹配上, 把之前的任务数量回滚
                        rollbackSupplierRestCapacity(mapEngineTaskVsSupplierTask);
                        // 之后的任务不匹配, 且整个主机厂不会加入集合中
                        continue startEachEngineFactory;
                    }
                } else {
                    // 主机厂任务数量匹配到的服务 > 1

                    // 每个任务都new一个暂存结果的有序集合(key大的排前面)
                    TreeMap<Double, SupplierTask> mapTmp = new TreeMap<>(((o1, o2) -> o2 - o1 > 0 ? 1 : 0));
                    // 循环计算匹配度, 高的存入
                    for (SupplierTask task : listMatchingSupplierTasks) {
                        double matchingDegree = CalculationUtils.calMatchingDegree(aEngineFactoryManufacturingTask, task, mapRelationshipMatrix);
                        mapTmp.put(matchingDegree, task);
                    }

                    // 看看有没有匹配上的flag
                    boolean flag = false;
                    for (Map.Entry<Double, SupplierTask> doubleSupplierTaskEntry : mapTmp.entrySet()) {
                        // 匹配度
                        Double matchDegree = doubleSupplierTaskEntry.getKey();

                        // 供应商
                        supplierTask = doubleSupplierTaskEntry.getValue();
                        // 主机厂需要量
                        engineFactoryNeedServiceNumber = aEngineFactoryManufacturingTask.getEngineFactoryNeedServiceNumber();
                        // 供应商还能提供量
                        supplierRestCapacity = supplierTask.getSupplierRestCapacity();
                        // 判断供应商是否有产能
                        if (supplierRestCapacity > engineFactoryNeedServiceNumber) {
                            // 匹配上了
                            aEngineFactoryManufacturingTask.setMatchDegree(matchDegree);

                            // 还有产能
                            // 供应商的剩余产能为: 相减
                            supplierRestCapacity = supplierRestCapacity - engineFactoryNeedServiceNumber;
                            supplierTask.setSupplierRestCapacity(supplierRestCapacity);
                            // 插入匹配map中
                            mapEngineTaskVsSupplierTask.put(aEngineFactoryManufacturingTask, supplierTask);
                            flag = true;
                            break;
                        }
                    }
                }
            }
            // 加入匹配成功集合
            listMapEngineFactoryTaskVsSupplierTask.add(mapEngineTaskVsSupplierTask);
        }
        ArrayList<TransactionContract> listTransactionContract = genTransactionContracts(listMapEngineFactoryTaskVsSupplierTask);
        return listTransactionContract;
    }

    /**
     * 粗匹配(服务剩余产能)
     *
     * @param engineFactoryManufacturingTask 主机厂任务
     * @param supplierTasks                  对应的所有服务集合
     * @return 匹配结果集合(0, 1, > 1)
     */
    private ArrayList<SupplierTask> roughMatching(EngineFactoryManufacturingTask engineFactoryManufacturingTask, ArrayList<SupplierTask> supplierTasks) {
        ArrayList<SupplierTask> listMatchingSupplierTasks = new ArrayList<>();

        // 需求为0就不匹配
        if (engineFactoryManufacturingTask.getEngineFactoryNeedServiceNumber() == 0) {
            return listMatchingSupplierTasks;
        }
        // 遍历所有类型相等的供应商
        for (SupplierTask aSupplierTask : supplierTasks) {
            // 任务期望质量<=服务质量
            if (engineFactoryManufacturingTask.getEngineFactoryExpectedQuality() <= aSupplierTask.getSupplierQuality()) {
                // 两者期望价格有交集 或者 主机厂的价格下限大于供应商的价格上限
                if (CalculationUtils.whetherPriceIntersection(engineFactoryManufacturingTask, aSupplierTask)
                        || engineFactoryManufacturingTask.getEngineFactory2ServiceOfferPrice()[0] >= aSupplierTask.getSupplierPriceRange()[1]) {
                    // 任务要求产量<=服务剩余产能
                    if (engineFactoryManufacturingTask.getEngineFactoryNeedServiceNumber() <= aSupplierTask.getSupplierRestCapacity()) {
                        // 三个条件都满足, 加入匹配上的数组里
                        listMatchingSupplierTasks.add(aSupplierTask);
                    }
                }
            }
        }
        return listMatchingSupplierTasks;
    }

    /**
     * 获取订单价格
     *
     * @param engineFactoryManufacturingTask 主机厂任务
     * @param supplierTask                   唯一匹配供应商服务
     * @return 订单价格
     */
    private int genTransactionContractOrderPrice(EngineFactoryManufacturingTask engineFactoryManufacturingTask, SupplierTask supplierTask) {
        int res;
        if (CalculationUtils.whetherPriceIntersection(engineFactoryManufacturingTask, supplierTask)) {
            // 价格有交集, 在交集上取
            res = CalculationUtils.genTransactionContractOrderPrice(engineFactoryManufacturingTask, supplierTask, true);
        } else {
            // 无交集, 在并集上取
            res = CalculationUtils.genTransactionContractOrderPrice(engineFactoryManufacturingTask, supplierTask, false);
        }
        return res;
    }

    /**
     * 生成交易契约
     *
     * @param listMapEngineFactoryTaskVsSupplierTask 精匹配第二阶段匹配的结果
     * @return 交易契约集合
     */
    private ArrayList<TransactionContract> genTransactionContracts(ArrayList<LinkedHashMap<EngineFactoryManufacturingTask, SupplierTask>> listMapEngineFactoryTaskVsSupplierTask) {
        ArrayList<TransactionContract> listRes = new ArrayList<>();
        for (LinkedHashMap<EngineFactoryManufacturingTask, SupplierTask> map : listMapEngineFactoryTaskVsSupplierTask) {
            for (Map.Entry<EngineFactoryManufacturingTask, SupplierTask> entry : map.entrySet()) {
                EngineFactoryManufacturingTask engineFactoryManufacturingTask = entry.getKey();
                SupplierTask supplierTask = entry.getValue();
                // ____需要存的交易契约模型
                TransactionContract transactionContract = new TransactionContract();
                transactionContract.setEngineFactoryId(engineFactoryManufacturingTask.getEngineFactoryId());
                transactionContract.setEngineFactoryCredit(engineFactoryManufacturingTask.getEngineFactoryCredit());
                transactionContract.setEngineFactoryLocationXY(engineFactoryManufacturingTask.getEngineFactoryLocationXY());
                transactionContract.setSupplierId(supplierTask.getSupplierId());
                transactionContract.setSupplierCredit(supplierTask.getSupplierCredit());
                transactionContract.setSupplierLocationXY(supplierTask.getSupplierLocationXY());
                transactionContract.setTaskType(engineFactoryManufacturingTask.getTaskType());
                transactionContract.setEngineFactoryNeedServiceNumber(engineFactoryManufacturingTask.getEngineFactoryNeedServiceNumber());
                transactionContract.setOrderPrice(genTransactionContractOrderPrice(engineFactoryManufacturingTask, supplierTask));
                transactionContract.setOrderQuality(supplierTask.getSupplierQuality());
                // 匹配度
                transactionContract.setMatchDegree(engineFactoryManufacturingTask.getMatchDegree());

                // 初始期望价格
                transactionContract.setEngineFactory2ServiceOfferPrice(engineFactoryManufacturingTask.getEngineFactory2ServiceOfferPrice());

                listRes.add(transactionContract);
            }
        }
        return listRes;
    }

    /**
     * 回滚供应商的剩余产能
     *
     * @param mapEngineFactoryTaskVsSupplierTask 之前已经匹配上的任务与供应商的集合
     */
    private void rollbackSupplierRestCapacity(LinkedHashMap<EngineFactoryManufacturingTask, SupplierTask> mapEngineFactoryTaskVsSupplierTask) {
        EngineFactoryManufacturingTask engineFactoryManufacturingTask;
        SupplierTask supplierTask;
        int engineFactoryNeedServiceNumber;
        int supplierRestCapacity;
        if (!mapEngineFactoryTaskVsSupplierTask.isEmpty()) {
            for (Map.Entry<EngineFactoryManufacturingTask, SupplierTask> engineFactoryManufacturingTaskSupplierTaskEntry : mapEngineFactoryTaskVsSupplierTask.entrySet()) {
                engineFactoryManufacturingTask = engineFactoryManufacturingTaskSupplierTaskEntry.getKey();
                supplierTask = engineFactoryManufacturingTaskSupplierTaskEntry.getValue();
                // 当时主机厂需要的数量
                engineFactoryNeedServiceNumber = engineFactoryManufacturingTask.getEngineFactoryNeedServiceNumber();
                // 剩余产能
                supplierRestCapacity = supplierTask.getSupplierRestCapacity();
                supplierRestCapacity += engineFactoryNeedServiceNumber;
                // 回滚
                supplierTask.setSupplierRestCapacity(supplierRestCapacity);
            }
        }
    }

    /**
     * 用于重新匹配任务
     * 前提: 有剩余产能
     *
     * @param engineFactoryManufacturingTask 主机厂单个任务
     * @param listSupplierTask               供应商对应任务类型的服务集合
     * @return 供应商能匹配上的服务集合
     */
    private ArrayList<SupplierTask> reGenListMatchingSupplierTask(EngineFactoryManufacturingTask engineFactoryManufacturingTask, ArrayList<SupplierTask> listSupplierTask) {
        // 结果集
        ArrayList<SupplierTask> listRes = new ArrayList<>();

        // 需求为0就不匹配
        if (engineFactoryManufacturingTask.getEngineFactoryNeedServiceNumber() == 0) {
            return listRes;
        }
//        if (listSupplierTask.symbolSize() != 0) {
//            log.info("+++++++++++++++" + listSupplierTask.get(0).getSupplierType());
//        } else {
//            log.info("+++++++++++++++供应商任务分解为0");
//        }
        // 用供应商集合
        for (SupplierTask supplierTask : listSupplierTask) {
            // 每个循环是供应商的能提供的任务

            // 任务需求量
            int engineFactoryNeedServiceNumber = engineFactoryManufacturingTask.getEngineFactoryNeedServiceNumber();
            // 服务商剩余产能
            int supplierRestCapacity = supplierTask.getSupplierRestCapacity();

            // 任务要求产量<=服务剩余产能
//            log.info("supplierRestCapacity  :  " + supplierRestCapacity);
//            log.info("engineFactoryNeedServiceNumber < supplierRestCapacity   " + (engineFactoryNeedServiceNumber < supplierRestCapacity));
            if (engineFactoryNeedServiceNumber < supplierRestCapacity) {

                //
                int engineFactoryExpectedQuality = engineFactoryManufacturingTask.getEngineFactoryExpectedQuality();
                //
                int[] engineFactory2ServiceOfferPrice = engineFactoryManufacturingTask.getEngineFactory2ServiceOfferPrice();
//                log.error("#START  ####engineFactory2ServiceOfferPrice####");
//                log.info(engineFactory2ServiceOfferPrice[0] + "  " + engineFactory2ServiceOfferPrice[1]);
//                log.info("  ");
//                log.info("#START  quality");
//                log.info(engineFactoryExpectedQuality+" ");
//                log.info("  ");
//                log.info("###供应商质量  " + supplierTask.getSupplierQuality());
//                log.info("###供应商出价  " + supplierTask.getSupplierPriceRange()[0] + "  " + supplierTask.getSupplierPriceRange()[1]);

                // 供应商用供应能力, 一定能匹配上
                boolean flag = false;
                while (!flag) {
                    // 主机厂质量 +(-1)
//                    int engineFactoryExpectedQuality = engineFactoryManufacturingTask.getEngineFactoryExpectedQuality();
                    engineFactoryExpectedQuality = engineFactoryExpectedQuality >= NumberEnum.QUALITY_LOW_LIMIT
                            ? engineFactoryExpectedQuality + CalculationEnum.unassignedTaskRq : engineFactoryExpectedQuality;
                    // 主机厂价格区间 * 1.1 取整
//                    int[] engineFactory2ServiceOfferPrice = engineFactoryManufacturingTask.getEngineFactory2ServiceOfferPrice();


//                    log.info(engineFactory2ServiceOfferPrice[0] + "  " + engineFactory2ServiceOfferPrice[1]);


                    engineFactory2ServiceOfferPrice = new int[]{
                            ((int) (engineFactory2ServiceOfferPrice[NumberEnum.PRICE_LOW_ARRAY_INDEX] * CalculationEnum.unassignedTaskRc) + 1)
                            , ((int) (engineFactory2ServiceOfferPrice[NumberEnum.PRICE_UPPER_ARRAY_INDEX] * CalculationEnum.unassignedTaskRc) + 1)};

//                    engineFactoryManufacturingTask.setEngineFactoryExpectedQuality(engineFactoryExpectedQuality);
//                    engineFactoryManufacturingTask.setEngineFactory2ServiceOfferPrice(engineFactory2ServiceOfferPrice);
                    // 有匹配的就加
                    // 任务期望质量<=服务质量
                    if (engineFactoryExpectedQuality <= supplierTask.getSupplierQuality()) {
                        // 两者期望价格有交集 或者 主机厂的价格下限大于供应商的价格上限
//                        if (CalculationUtils.whetherPriceIntersection(engineFactoryManufacturingTask, supplierTask)
//                           || engineFactoryManufacturingTask.getEngineFactory2ServiceOfferPrice()[NumberEnum.PRICE_LOW_ARRAY_INDEX]
//                                >= supplierTask.getSupplierPriceRange()[NumberEnum.PRICE_UPPER_ARRAY_INDEX]) {

                            if (CalculationUtils.whetherPriceIntersection(engineFactory2ServiceOfferPrice, supplierTask.getSupplierPriceRange())
                                || engineFactory2ServiceOfferPrice[NumberEnum.PRICE_LOW_ARRAY_INDEX]
                                >= supplierTask.getSupplierPriceRange()[NumberEnum.PRICE_UPPER_ARRAY_INDEX]) {

                            // 三个条件都满足, 加入匹配上的数组里
                            listRes.add(supplierTask);
//                            log.info("  end  " + engineFactory2ServiceOfferPrice[0] + "  " + engineFactory2ServiceOfferPrice[1]);
//                            log.info("  end  " + engineFactoryExpectedQuality);

                            flag = true;
                        }
                    }
                }
            }
        }
        return listRes;
    }

    /**
     * 通过type值得到服务集合中对应的服务集合
     *
     * @param listListSupplierTask 供应商服务集合(里面包含4种任务的集合)
     * @param taskType             任务代码
     * @return 与任务代码对应的供应商服务集合
     */
    private ArrayList<SupplierTask> getSupplierTasks(ArrayList<ArrayList<SupplierTask>> listListSupplierTask, int taskType) {
        ArrayList<SupplierTask> supplierTasks;
        switch (taskType) {
            case 210:
                supplierTasks = listListSupplierTask.get(0);
                break;
            case 220:
                supplierTasks = listListSupplierTask.get(1);
                break;
            case 230:
                supplierTasks = listListSupplierTask.get(2);
                break;
            case 240:
                supplierTasks = listListSupplierTask.get(3);
                break;
            case 250:
                supplierTasks = listListSupplierTask.get(4);
                break;
            default:
                throw new RuntimeException("no such task type");
        }
        return supplierTasks;
    }
    //-----------------------------------交易契约相关------------------------------------------

}
