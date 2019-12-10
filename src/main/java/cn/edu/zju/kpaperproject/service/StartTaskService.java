package cn.edu.zju.kpaperproject.service;

import cn.edu.zju.kpaperproject.dto.EngineFactoryManufacturingTask;
import cn.edu.zju.kpaperproject.dto.SupplierTask;

import java.util.ArrayList;
import java.util.Map;

/**
 * 任务计算开始前
 *
 * @author RichardLee
 * @version v1.0
 */
public interface StartTaskService {
    /**
     * 生成主机厂分解任务
     * <p>
     * 返回值按信誉度从高到底排, 信誉度相同就按210任务出价从高到底排
     *
     * @param experimentsNumber 实验次数
     * @param cycleTime         循环的次数, 从1开始
     * @return                  返回值中每个元素代表一个主机厂分解的任务集
     */
    ArrayList<ArrayList<EngineFactoryManufacturingTask>> genEngineFactoryTaskDecomposition(int experimentsNumber, int cycleTime);

    /**
     * 供应商提供的服务
     * 如出价/质量/产能等
     * <p>
     * 返回值索引0~4就是能提供任务类型210~250的各个供应商服务
     *
     * @param experimentsNumber 实验次数
     * @param cycleTime         循环的次数, 从1开始
     * @return                  返回值中每个元素代表提供某类型服务供应商集合
     */
    ArrayList<ArrayList<SupplierTask>> genSupplierTask(int experimentsNumber, int cycleTime);

    /**
     * 获得主机厂与供应商之间的关系矩阵
     * key   = 主机厂id + 供应商id
     * value = 两者关系强度
     *
     * @param experimentsNumber 实验次数
     * @param cycleTime         循环次数
     * @return                  循环次数 - 1 时(最新的)关系矩阵
     */
    Map<String, Double> getMapRelationshipMatrix(int experimentsNumber, int cycleTime);

}
