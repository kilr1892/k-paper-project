package cn.edu.zju.kpaperproject.service;

import cn.edu.zju.kpaperproject.dto.EngineFactoryManufacturingTask;

import java.util.ArrayList;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
public interface OrderService {
    /**
     * 生成主机厂任务
     * @return 每个主机厂的任务
     */
    ArrayList<EngineFactoryManufacturingTask> genTask();
}
