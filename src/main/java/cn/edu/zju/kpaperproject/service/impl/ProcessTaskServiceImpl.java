package cn.edu.zju.kpaperproject.service.impl;

import cn.edu.zju.kpaperproject.dto.EngineFactoryManufacturingTask;
import cn.edu.zju.kpaperproject.dto.SupplierTask;
import cn.edu.zju.kpaperproject.service.ProcessTaskService;
import cn.edu.zju.kpaperproject.utils.CalculationUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;

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
     * @param listListEngineFactoryTasks    主机厂分解任务集合(按)
     * @param listListSupplierTask          供应商能提供的服务集合
     * @return                              主机任务(key)对应匹配上的服务集合(value)
     */
    @Override
    public LinkedHashMap<EngineFactoryManufacturingTask, ArrayList<SupplierTask>>
    roughMatching(ArrayList<ArrayList<EngineFactoryManufacturingTask>> listListEngineFactoryTasks, ArrayList<ArrayList<SupplierTask>> listListSupplierTask) {

        // # 返回匹配的对象: LinkedMap<任务集合, 服务集>
        LinkedHashMap<EngineFactoryManufacturingTask, ArrayList<SupplierTask>> mapRes = new LinkedHashMap<>();

        for (ArrayList<EngineFactoryManufacturingTask> listEngineFactoryTask : listListEngineFactoryTasks) {
            // 每个主机厂任务集合  listEngineFactoryTask
            for (EngineFactoryManufacturingTask aEngineFactoryManufacturingTask : listEngineFactoryTask) {
                // 每个主机厂任务 aEngineFactoryManufacturingTask
                // __匹配上的服务
                ArrayList<SupplierTask> listMatchingSupplierTasks = new ArrayList<>();
                // 初始对应的服务集合
                ArrayList<SupplierTask> supplierTasks;
                // # 条件
                // 任务类型相同
                int taskType = aEngineFactoryManufacturingTask.getTaskType();
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
                // 遍历所有类型相等的供应商
                for (SupplierTask aSupplierTask : supplierTasks) {
                    // 任务期望质量<=服务质量
                    if (aEngineFactoryManufacturingTask.getEngineFactoryExpectedQuality() <= aSupplierTask.getSupplierQuality()) {
                        // 两者期望价格有交集 或者 主机厂的价格下限大于供应商的价格上限
                        if (CalculationUtils.whetherPriceIntersection(aEngineFactoryManufacturingTask.getEngineFactory2ServiceOfferPrice(), aSupplierTask.getSupplierPriceRange())
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
                mapRes.put(aEngineFactoryManufacturingTask, listMatchingSupplierTasks);
            }
        }
        return mapRes;
    }
    /** 精匹配方法 */
    /** 未匹配方法 */
    /** 重新匹配方法 */

}
