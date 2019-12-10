package cn.edu.zju.kpaperproject.service.impl;

import cn.edu.zju.kpaperproject.dto.EngineFactoryManufacturingTask;
import cn.edu.zju.kpaperproject.dto.SupplierTask;
import cn.edu.zju.kpaperproject.enums.CalculationEnum;
import cn.edu.zju.kpaperproject.enums.NumberEnum;
import cn.edu.zju.kpaperproject.service.ProcessTaskService;
import cn.edu.zju.kpaperproject.utils.CalculationUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * .
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
    public void exactMatching
    (ArrayList<LinkedHashMap<EngineFactoryManufacturingTask, ArrayList<SupplierTask>>> listLinkedHashMapEngineTaskMatchingSupplierTask, ArrayList<ArrayList<SupplierTask>> listListSupplierTask) {
        if (listLinkedHashMapEngineTaskMatchingSupplierTask == null || listLinkedHashMapEngineTaskMatchingSupplierTask.size() == 0) {
            throw new RuntimeException("粗匹配无一个数据");
        }

        // # 第一阶段, 补全为空的, 过滤没用的集合
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
        // TODO 假设能删除成功了, 不成功就new一个list放进去
        Iterator<LinkedHashMap<EngineFactoryManufacturingTask, ArrayList<SupplierTask>>> iterator = listLinkedHashMapEngineTaskMatchingSupplierTask.iterator();
        deleteHave0EngineFactory:
        while (iterator.hasNext()) {
            LinkedHashMap<EngineFactoryManufacturingTask, ArrayList<SupplierTask>> mapEngineFactory = iterator.next();
            for (Map.Entry<EngineFactoryManufacturingTask, ArrayList<SupplierTask>> entry : mapEngineFactory.entrySet()) {
                ArrayList<SupplierTask> listSupplierTask = entry.getValue();
                if (listSupplierTask == null || listSupplierTask.size() == 0) {
                    iterator.remove();
                    break deleteHave0EngineFactory;
                }
            }
        }

        // # 第二阶段, 按size的情况来计算
        listMapMatchingTask:
        for (int i = 0; i < listLinkedHashMapEngineTaskMatchingSupplierTask.size(); i++) {
            // TODO 这里准备个新的集合, 再过滤一次, 把有为0的过滤掉

            // 取出主机厂的任务匹配集
            LinkedHashMap<EngineFactoryManufacturingTask, ArrayList<SupplierTask>> mapEngineFactory = listLinkedHashMapEngineTaskMatchingSupplierTask.get(i);
            // 取出一个主机厂的所有的任务集合
            for (Map.Entry<EngineFactoryManufacturingTask, ArrayList<SupplierTask>> entry : mapEngineFactory.entrySet()) {
                EngineFactoryManufacturingTask factoryManufacturingTask = entry.getKey();
                ArrayList<SupplierTask> listSupplierTask = entry.getValue();

                switch (listSupplierTask.size()) {
                    case 1:
                        // 主机厂任务数量匹配到的服务 == 1
                        // TODO 计算成交量
                        break;
                    default:
                        // 主机厂任务数量匹配到的服务 > 1
                        // 循环
                        for (SupplierTask supplierTask : listSupplierTask) {
                            // TODO 算关系强度
                            // TODO 计算成交量
                        }
                        break;
                }

            }
        }

        // TODO 返回值为一个list


    }

    /** 用于重新匹配任务 */
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
