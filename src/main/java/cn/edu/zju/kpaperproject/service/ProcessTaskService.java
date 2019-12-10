package cn.edu.zju.kpaperproject.service;

import cn.edu.zju.kpaperproject.dto.EngineFactoryManufacturingTask;
import cn.edu.zju.kpaperproject.dto.SupplierTask;
import cn.edu.zju.kpaperproject.dto.TransactionContract;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
public interface ProcessTaskService {

    /**
     * 粗匹配方法
     *
     * @param listListEngineFactoryTasks 主机厂分解任务集合(按信誉排的)
     * @param listListSupplierTask       供应商能提供的服务集合
     * @return list中每个元素代表一个主机厂的粗匹配任务集
     */
    ArrayList<LinkedHashMap<EngineFactoryManufacturingTask, ArrayList<SupplierTask>>> roughMatching(ArrayList<ArrayList<EngineFactoryManufacturingTask>> listListEngineFactoryTasks
            , ArrayList<ArrayList<SupplierTask>> listListSupplierTask);


    /**
     * 精匹配方法
     *
     * @param listLinkedHashMapEngineTaskMatchingSupplierTask   粗匹配结果
     * @param listListSupplierTask                              供应商能提供的服务集合(外list是按服务类型分的)
     * @param mapRelationshipMatrix                             双方关系强度Map
     * @return                                                  交易契约集合
     */
    ArrayList<TransactionContract> exactMatching(ArrayList<LinkedHashMap<EngineFactoryManufacturingTask, ArrayList<SupplierTask>>> listLinkedHashMapEngineTaskMatchingSupplierTask
            , ArrayList<ArrayList<SupplierTask>> listListSupplierTask
            , Map<String, Double> mapRelationshipMatrix);

    /** 未匹配方法 */
    /** 重新匹配方法 */
}
