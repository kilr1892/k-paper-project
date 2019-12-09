package cn.edu.zju.kpaperproject.service;

import cn.edu.zju.kpaperproject.dto.EngineFactoryManufacturingTask;
import cn.edu.zju.kpaperproject.dto.SupplierTask;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
public interface ProcessTaskService {

    /** 粗匹配方法 */
    LinkedHashMap<EngineFactoryManufacturingTask, ArrayList<SupplierTask>>
    roughMatching(ArrayList<ArrayList<EngineFactoryManufacturingTask>> listListEngineFactoryTasks, ArrayList<ArrayList<SupplierTask>> listListSupplierTask);


    /** 精匹配方法 */
    /** 未匹配方法 */
    /** 重新匹配方法 */
}
