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

    /**
     * 粗匹配方法
     *
     * @param listListEngineFactoryTasks 主机厂分解任务集合(按信誉排的)
     * @param listListSupplierTask       供应商能提供的服务集合
     * @return list中每个元素代表一个主机厂的粗匹配任务集
     */
    @Override
    public ArrayList<LinkedHashMap<EngineFactoryManufacturingTask, ArrayList<SupplierTask>>>
    roughMatching(ArrayList<ArrayList<EngineFactoryManufacturingTask>> listListEngineFactoryTasks, ArrayList<ArrayList<SupplierTask>> listListSupplierTask) {
        // 返回值
        ArrayList<LinkedHashMap<EngineFactoryManufacturingTask, ArrayList<SupplierTask>>> resList = new ArrayList<>();

        for (ArrayList<EngineFactoryManufacturingTask> listEngineFactoryTask : listListEngineFactoryTasks) {
            // LinkedHashMap<任务集合, 服务集>
            // 一个主机厂一个Map
            LinkedHashMap<EngineFactoryManufacturingTask, ArrayList<SupplierTask>> engineTaskVsListSupplierTask = new LinkedHashMap<>();
            // 每个主机厂的5个任务集合  listEngineFactoryTask
            for (EngineFactoryManufacturingTask aEngineFactoryManufacturingTask : listEngineFactoryTask) {
                // 每个主机厂任务 aEngineFactoryManufacturingTask
                // __匹配上的服务
                ArrayList<SupplierTask> listMatchingSupplierTasks = new ArrayList<>();
                // 初始对应的服务集合
                ArrayList<SupplierTask> supplierTasks;
                // # 条件
                // 任务类型相同
                int taskType = aEngineFactoryManufacturingTask.getTaskType();
                supplierTasks = getSupplierTasks(listListSupplierTask, taskType);
                // 遍历所有类型相等的供应商
                for (SupplierTask aSupplierTask : supplierTasks) {
                    // 任务期望质量<=服务质量
                    if (aEngineFactoryManufacturingTask.getEngineFactoryExpectedQuality() <= aSupplierTask.getSupplierQuality()) {
                        // 两者期望价格有交集 或者 主机厂的价格下限大于供应商的价格上限
                        if (CalculationUtils.whetherPriceIntersection(aEngineFactoryManufacturingTask, aSupplierTask)
                                || aEngineFactoryManufacturingTask.getEngineFactory2ServiceOfferPrice()[0] >= aSupplierTask.getSupplierPriceRange()[1]) {
                            // 任务要求产量<=服务产能
                            if (aEngineFactoryManufacturingTask.getEngineFactoryNeedServiceNumber() <= aSupplierTask.getSupplierCapacity()) {
                                // 三个条件都满足, 加入匹配上的数组里
                                listMatchingSupplierTasks.add(aSupplierTask);
                            }
                        }
                    }
                }
                // 遍历完成, 把任务和粗匹配上的供应商加入map里
                engineTaskVsListSupplierTask.put(aEngineFactoryManufacturingTask, listMatchingSupplierTasks);
            }
            resList.add(engineTaskVsListSupplierTask);
        }
        return resList;
    }

    /** 精匹配方法 */
    @Override
    public ArrayList<TransactionContract>
    exactMatching(ArrayList<LinkedHashMap<EngineFactoryManufacturingTask, ArrayList<SupplierTask>>> listLinkedHashMapEngineTaskMatchingSupplierTask
            , ArrayList<ArrayList<SupplierTask>> listListSupplierTask
            , Map<String, Double> mapRelationshipMatrix) {

        if (listLinkedHashMapEngineTaskMatchingSupplierTask == null || listLinkedHashMapEngineTaskMatchingSupplierTask.size() == 0) {
            throw new RuntimeException("粗匹配无一个数据");
        }
        // # 第一阶段, size为0的重匹配, 过滤真的匹配不上的主机厂
        exactMatchingPart1(listLinkedHashMapEngineTaskMatchingSupplierTask, listListSupplierTask);

        // # 第二阶段, 按size的情况来计算, 生成一个最终匹配上的矩阵, 之后再计算成交量
        // 生成一个最终匹配上的一对一
        ArrayList<LinkedHashMap<EngineFactoryManufacturingTask, SupplierTask>> finalMatchEngineTaskVsSupplierTask
                = getFinalMatchEngineTaskVsSupplierTask(listLinkedHashMapEngineTaskMatchingSupplierTask, mapRelationshipMatrix);

        // 再获取 任务类型/主机厂需求量/价格/质量, 这个作为精匹配的返回值

        // 暂时不分厂做集合
        ArrayList<TransactionContract> listRes = new ArrayList<>();
        // TODO 返回 值为一个list
        for (LinkedHashMap<EngineFactoryManufacturingTask, SupplierTask> map : finalMatchEngineTaskVsSupplierTask) {
            for (Map.Entry<EngineFactoryManufacturingTask, SupplierTask> entry : map.entrySet()) {
                EngineFactoryManufacturingTask engineFactoryManufacturingTask = entry.getKey();
                SupplierTask supplierTask = entry.getValue();
                // ____需要存的交易契约模型
                TransactionContract transactionContract = new TransactionContract();
                transactionContract.setEngineFactoryId(engineFactoryManufacturingTask.getEngineFactoryId());
                transactionContract.setSupplierId(supplierTask.getSupplierId());
                transactionContract.setTaskType(engineFactoryManufacturingTask.getTaskType());
                transactionContract.setEngineFactoryNeedServiceNumber(engineFactoryManufacturingTask.getEngineFactoryNeedServiceNumber());
                transactionContract.setOrderPrice(genTransactionContractOrderPrice(engineFactoryManufacturingTask,supplierTask));
                transactionContract.setOrderQuality(supplierTask.getSupplierQuality());

                listRes.add(transactionContract);
            }
        }
        return listRes;
    }

    /**
     * 获取订单价格
     *
     * @param engineFactoryManufacturingTask    主机厂任务
     * @param supplierTask                      唯一匹配供应商服务
     * @return                                  订单价格
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
     * 最终的唯一匹配结果
     * 会对供应商进行剩余产能计算, 若剩余产能不足, 则匹配不上
     * 一旦有一个任务匹配不上, 整个主机厂的任务会被抹去(过滤主机厂)
     * 
     * list中每个元素(有序map)代表一个厂所有任务以及对应的唯一服务
     *
     * @param listLinkedHashMapEngineTaskMatchingSupplierTask   主机厂任务匹配到多个服务的集合
     * @param mapRelationshipMatrix                             主机厂与服务商的关系强度
     * @return                                                  最终主机厂与服务商的唯一配对结果集合
     */
    private ArrayList<LinkedHashMap<EngineFactoryManufacturingTask, SupplierTask>> getFinalMatchEngineTaskVsSupplierTask(ArrayList<LinkedHashMap<EngineFactoryManufacturingTask, ArrayList<SupplierTask>>> listLinkedHashMapEngineTaskMatchingSupplierTask, Map<String, Double> mapRelationshipMatrix) {
        ArrayList<LinkedHashMap<EngineFactoryManufacturingTask, SupplierTask>> listRes2 = new ArrayList<>();
        
        
        // 用于过滤没有匹配到任务的厂子
        startTraverseEngineTasks:
        for (int i = 0; i < listLinkedHashMapEngineTaskMatchingSupplierTask.size(); i++) {
            // 每个厂new返回值中的一个元素
            // 代表每个主机厂, 里面有所有任务 -- 匹配上的唯一服务的Map集合
            LinkedHashMap<EngineFactoryManufacturingTask, SupplierTask> mapEngineFactoryTaskVsSupplierTask = new LinkedHashMap<>();

            // 取出主机厂的任务匹配集
            LinkedHashMap<EngineFactoryManufacturingTask, ArrayList<SupplierTask>> mapEngineFactory = listLinkedHashMapEngineTaskMatchingSupplierTask.get(i);

            // 部分可以重用的变量
            // 供应商
            SupplierTask supplierTask;
            // 主机厂任务
            EngineFactoryManufacturingTask engineFactoryManufacturingTask;
            // 主机厂需要量
            int engineFactoryNeedServiceNumber;
            // 供应商还能提供量
            int supplierRestCapacity;

            // 取出一个主机厂的所有的任务集合
            for (Map.Entry<EngineFactoryManufacturingTask, ArrayList<SupplierTask>> entry : mapEngineFactory.entrySet()) {
                // 一个主机厂的一个任务
                engineFactoryManufacturingTask = entry.getKey();
                // 匹配上的服务
                ArrayList<SupplierTask> listSupplierTask = entry.getValue();

                // 按服务数量匹配
                if (listSupplierTask.size() == 1) {
                    // 主机厂任务数量匹配到的服务 == 1
                    // 供应商
                    supplierTask = listSupplierTask.get(0);
                    // 主机厂需要量
                    engineFactoryNeedServiceNumber = engineFactoryManufacturingTask.getEngineFactoryNeedServiceNumber();
                    // 供应商还能提供量
                    supplierRestCapacity = supplierTask.getSupplierRestCapacity();
                    // 判断供应商是否有产能
                    if (supplierRestCapacity > engineFactoryNeedServiceNumber) {
                        // 供应商的剩余产能相减
                        supplierRestCapacity = supplierRestCapacity - engineFactoryNeedServiceNumber;
                        supplierTask.setSupplierRestCapacity(supplierRestCapacity);
                        // 插入匹配map中
                        mapEngineFactoryTaskVsSupplierTask.put(engineFactoryManufacturingTask, supplierTask);
                    } else {
                        // 没匹配上, 把之前的任务数量回滚
                        rollbackSupplierRestCapacity(mapEngineFactoryTaskVsSupplierTask);
                        // 之后的任务不匹配, 且整个主机厂不会加入集合中
                        break startTraverseEngineTasks;
                    }
                } else {
                    // 主机厂任务数量匹配到的服务 > 1

                    // 每个任务都new一个暂存结果的有序集合(key大的排前面)
                    TreeMap<Double, SupplierTask> mapTmp = new TreeMap<>(((o1, o2) -> o2 - o1 > 0 ? 1 : 0));
                    // 循环计算匹配度, 高的存入
                    for (SupplierTask task : listSupplierTask) {
                        double matchingDegree = CalculationUtils.calMatchingDegree(engineFactoryManufacturingTask, task, mapRelationshipMatrix);
                        mapTmp.put(matchingDegree, task);
                    }

                    // 看看有没有匹配上的flag
                    boolean flag = false;
                    for (Map.Entry<Double, SupplierTask> doubleSupplierTaskEntry : mapTmp.entrySet()) {
                        // 供应商
                        supplierTask = doubleSupplierTaskEntry.getValue();
                        // 主机厂需要量
                        engineFactoryNeedServiceNumber = engineFactoryManufacturingTask.getEngineFactoryNeedServiceNumber();
                        // 供应商还能提供量
                        supplierRestCapacity = supplierTask.getSupplierRestCapacity();
                        // 判断供应商是否有产能
                        if (supplierRestCapacity > engineFactoryNeedServiceNumber) {
                            // 还有产能
                            // 供应商的剩余产能为: 相减
                            supplierRestCapacity = supplierRestCapacity - engineFactoryNeedServiceNumber;
                            supplierTask.setSupplierRestCapacity(supplierRestCapacity);
                            // 插入匹配map中
                            mapEngineFactoryTaskVsSupplierTask.put(engineFactoryManufacturingTask, supplierTask);
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        rollbackSupplierRestCapacity(mapEngineFactoryTaskVsSupplierTask);
                        // 没匹配上了
                        break startTraverseEngineTasks;
                    }
                }
            }
            // 将一个主机厂所有任务 - 对应的服务的map, 加入返回集合中
            listRes2.add(mapEngineFactoryTaskVsSupplierTask);
        }
        return listRes2;
    }

    /**
     * 回滚供应商的剩余产能
     *
     * @param mapEngineFactoryTaskVsSupplierTask    之前已经匹配上的任务与供应商的集合
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
     * 精确匹配的阶段1
     * 没匹配上的重新匹配, 之后过滤到没有匹配上的厂子
     *
     * @param listLinkedHashMapEngineTaskMatchingSupplierTask 粗匹配的主机厂 - 供应商任务集
     * @param listListSupplierTask                            供应商服务集(按服务类型分)
     */
    private void exactMatchingPart1
    (ArrayList<LinkedHashMap<EngineFactoryManufacturingTask, ArrayList<SupplierTask>>> listLinkedHashMapEngineTaskMatchingSupplierTask, ArrayList<ArrayList<SupplierTask>> listListSupplierTask) {
        // 重匹配
        for (int i = 0; i < listLinkedHashMapEngineTaskMatchingSupplierTask.size(); i++) {
            // 取出一个厂
            LinkedHashMap<EngineFactoryManufacturingTask, ArrayList<SupplierTask>> mapEngineFactoryTaskVsSupplierTask = listLinkedHashMapEngineTaskMatchingSupplierTask.get(i);
            // 遍历工厂的每个任务
            for (Map.Entry<EngineFactoryManufacturingTask, ArrayList<SupplierTask>> entry : mapEngineFactoryTaskVsSupplierTask.entrySet()) {
                // 取出一个任务
                EngineFactoryManufacturingTask aEngineFactoryManufacturingTask = entry.getKey();
                // 任务粗匹配的集合
                ArrayList<SupplierTask> supplierTasks = entry.getValue();
                if (supplierTasks == null || supplierTasks.size() == 0) {
                    // 一个都没匹配上的, 再次匹配
                    ArrayList<SupplierTask> listNewMatchingSupplierTask = reGenListMatchingSupplierTask(aEngineFactoryManufacturingTask, listListSupplierTask);
                    // 匹配到了至少一个, 补全map集合
                    if (listListSupplierTask != null && listListSupplierTask.size() != 0) {
                        mapEngineFactoryTaskVsSupplierTask.put(aEngineFactoryManufacturingTask, listNewMatchingSupplierTask);
                    }
                }
            }
        }

        // 过滤还为0的厂: 整个主机厂都拿掉
        deleteHave0EngineFactory:
        for (int i = 0; i < listLinkedHashMapEngineTaskMatchingSupplierTask.size(); i++) {
            LinkedHashMap<EngineFactoryManufacturingTask, ArrayList<SupplierTask>> mapEngineFactory = listLinkedHashMapEngineTaskMatchingSupplierTask.get(i);
            for (Map.Entry<EngineFactoryManufacturingTask, ArrayList<SupplierTask>> entry : mapEngineFactory.entrySet()) {
                ArrayList<SupplierTask> listSupplierTask = entry.getValue();
                if (listSupplierTask == null || listSupplierTask.size() == 0) {
                    listLinkedHashMapEngineTaskMatchingSupplierTask.remove(i);
                    i--;
                    continue deleteHave0EngineFactory;
                }
            }
        }
    }

    /**
     * 用于重新匹配任务
     *
     * @param engineFactoryManufacturingTask 主机厂单个任务
     * @param listListSupplierTask           供应商对应能提供的任务集合
     * @return 供应商能匹配上的服务集合
     */
    private ArrayList<SupplierTask> reGenListMatchingSupplierTask(EngineFactoryManufacturingTask engineFactoryManufacturingTask, ArrayList<ArrayList<SupplierTask>> listListSupplierTask) {
        // 结果集
        ArrayList<SupplierTask> listRes = new ArrayList<>();

        // 获取对应的服务集合
        int taskType = engineFactoryManufacturingTask.getTaskType();
        ArrayList<SupplierTask> supplierTasks = getSupplierTasks(listListSupplierTask, taskType);

        if (supplierTasks == null || supplierTasks.size() == 0) {
            // 没有可用供应商
            return listRes;
        }
        // 用供应商集合
        for (SupplierTask supplierTask : supplierTasks) {
            int supplierCapacity = supplierTask.getSupplierCapacity();
            int engineFactoryNeedServiceNumber = engineFactoryManufacturingTask.getEngineFactoryNeedServiceNumber();
            // 供应商用供应能力, 一定能匹配上   任务要求产量<=服务产能
            if (engineFactoryNeedServiceNumber < supplierCapacity) {
                boolean flag = false;

                while (!flag) {
                    // 主机厂质量 -1
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

    /** 未匹配方法 */
    /** 重新匹配方法 */


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
}
