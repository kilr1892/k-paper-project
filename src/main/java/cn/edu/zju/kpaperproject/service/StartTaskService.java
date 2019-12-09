package cn.edu.zju.kpaperproject.service;

import cn.edu.zju.kpaperproject.dto.EngineFactoryManufacturingTask;
import cn.edu.zju.kpaperproject.dto.SupplierTask;

import java.util.ArrayList;

/**
 * 任务计算相关
 *
 * @author RichardLee
 * @version v1.0
 */
public interface StartTaskService {
    /**
     * 生成主机厂分解任务
     *
     * @param cycleTime     循环的次数, 从1开始
     * @return              返回值中每个元素代表一个主机厂分解的任务集
     */
    ArrayList<ArrayList<EngineFactoryManufacturingTask>> genEngineFactoryTaskDecomposition(int cycleTime);
    /**
     * 供应商提供的服务
     * 如出价/质量/产能等
     * <p>
     * 返回值索引 0~4 就是能提供任务类型 210~250 的各个供应商集合
     *
     * @param cycleTime 循环的次数, 从1开始
     * @return 返回值中每个元素代表提供某类型服务供应商集合
     */
    ArrayList<ArrayList<SupplierTask>> genSupplierTask(int cycleTime);
}
