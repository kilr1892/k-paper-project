package cn.edu.zju.kpaperproject.service.impl;

import cn.edu.zju.kpaperproject.dto.EngineFactoryManufacturingTask;
import cn.edu.zju.kpaperproject.dto.SupplierTask;
import cn.edu.zju.kpaperproject.dto.TransactionContract;
import cn.edu.zju.kpaperproject.enums.CalculationEnum;
import cn.edu.zju.kpaperproject.enums.NumberEnum;
import cn.edu.zju.kpaperproject.service.ProcessTaskService;
import cn.edu.zju.kpaperproject.utils.CalculationUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 任务处理相关的服务
 *
 * @author RichardLee
 * @version v1.0
 */
@Service
public class ProcessTaskServiceImpl implements ProcessTaskService {

    /** 交易结算 */
    public void getTransactionSettlement(
            ArrayList<TransactionContract> listTransactionContracts
            , Map<String, Double> mapRelationshipMatrix) {

        // 主机厂和供应商的履约概率
        double engineFactoryPerformanceProbability;
        double supplierPerformanceProbability;
        for (TransactionContract aTransactionContract : listTransactionContracts) {
            // 每次循环是每个交易契约

            // 计算双方履约概率
            engineFactoryPerformanceProbability = getPerformanceProbability(aTransactionContract, mapRelationshipMatrix);
            // 计算是否履约
            // 计算实际交易数量
            // 计算双方评分

        }
    }

    /**
     * 算履约概率的
     * @param transactionContract
     * @param mapRelationshipMatrix
     * @return
     */
    private double getPerformanceProbability(TransactionContract transactionContract, Map<String, Double> mapRelationshipMatrix) {
        transactionContract.

    }


    //-----------------------------------交易契约相关------------------------------------------
    /**
     * 获取交易契约
     * @param listListEngineFactoryTasks    按主机厂分的任务集合, 每个元素是一个主机厂集合(集合内元素是该主机厂的任务集)
     * @param listListSupplierTask          按任务分开后的供应商服务集合
     * @param mapRelationshipMatrix         关系强度
     * @return                              匹配上的主机厂与供应商的交易契约
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
                        // 供应商
                        supplierTask = doubleSupplierTaskEntry.getValue();
                        // 主机厂需要量
                        engineFactoryNeedServiceNumber = aEngineFactoryManufacturingTask.getEngineFactoryNeedServiceNumber();
                        // 供应商还能提供量
                        supplierRestCapacity = supplierTask.getSupplierRestCapacity();
                        // 判断供应商是否有产能
                        if (supplierRestCapacity > engineFactoryNeedServiceNumber) {
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
        ArrayList<TransactionContract> listRes = genTransactionContracts(listMapEngineFactoryTaskVsSupplierTask);
        return listRes;
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
                transactionContract.setSupplierId(supplierTask.getSupplierId());
                transactionContract.setSupplierCredit(supplierTask.getSupplierCredit());
                transactionContract.setTaskType(engineFactoryManufacturingTask.getTaskType());
                transactionContract.setEngineFactoryNeedServiceNumber(engineFactoryManufacturingTask.getEngineFactoryNeedServiceNumber());
                transactionContract.setOrderPrice(genTransactionContractOrderPrice(engineFactoryManufacturingTask, supplierTask));
                transactionContract.setOrderQuality(supplierTask.getSupplierQuality());

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

        // 用供应商集合
        for (SupplierTask supplierTask : listSupplierTask) {
            // 每个循环是供应商的能提供的任务

            // 任务需求量
            int engineFactoryNeedServiceNumber = engineFactoryManufacturingTask.getEngineFactoryNeedServiceNumber();
            // 服务商剩余产能
            int supplierRestCapacity = supplierTask.getSupplierRestCapacity();

            // 任务要求产量<=服务剩余产能
            if (engineFactoryNeedServiceNumber < supplierRestCapacity) {
                // 供应商用供应能力, 一定能匹配上
                boolean flag = false;
                while (!flag) {
                    // 主机厂质量 +(-1)
                    int engineFactoryExpectedQuality = engineFactoryManufacturingTask.getEngineFactoryExpectedQuality();
                    engineFactoryExpectedQuality = engineFactoryExpectedQuality >= NumberEnum.QUALITY_LOW_LIMIT
                            ? engineFactoryExpectedQuality + CalculationEnum.unassignedTaskRq : engineFactoryExpectedQuality;
                    // 主机厂价格区间 * 1.1 取整
                    int[] engineFactory2ServiceOfferPrice = engineFactoryManufacturingTask.getEngineFactory2ServiceOfferPrice();
                    engineFactory2ServiceOfferPrice = new int[]{
                            (int) (engineFactory2ServiceOfferPrice[NumberEnum.PRICE_LOW_ARRAY_INDEX] * CalculationEnum.unassignedTaskRc)
                            , (int) (engineFactory2ServiceOfferPrice[NumberEnum.PRICE_UPPER_ARRAY_INDEX] * CalculationEnum.unassignedTaskRc)};

                    engineFactoryManufacturingTask.setEngineFactoryExpectedQuality(engineFactoryExpectedQuality);
                    engineFactoryManufacturingTask.setEngineFactory2ServiceOfferPrice(engineFactory2ServiceOfferPrice);
                    // 有匹配的就加
                    // 任务期望质量<=服务质量

                    if (engineFactoryManufacturingTask.getEngineFactoryExpectedQuality() <= supplierTask.getSupplierQuality()) {
                        // 两者期望价格有交集 或者 主机厂的价格下限大于供应商的价格上限
                        if (CalculationUtils.whetherPriceIntersection(engineFactoryManufacturingTask, supplierTask)
                                || engineFactoryManufacturingTask.getEngineFactory2ServiceOfferPrice()[NumberEnum.PRICE_LOW_ARRAY_INDEX]
                                >= supplierTask.getSupplierPriceRange()[NumberEnum.PRICE_UPPER_ARRAY_INDEX]) {

                            // 三个条件都满足, 加入匹配上的数组里
                            listRes.add(supplierTask);
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
