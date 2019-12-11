package cn.edu.zju.kpaperproject.service;

import cn.edu.zju.kpaperproject.dto.EngineFactoryManufacturingTask;
import cn.edu.zju.kpaperproject.dto.SupplierTask;
import cn.edu.zju.kpaperproject.dto.TransactionContract;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 *
 * @author RichardLee
 * @version v1.0
 */
public interface ProcessTaskService {
    /**
     * 获取交易契约
     * @param listListEngineFactoryTasks    按主机厂分的任务集合, 每个元素是一个主机厂集合(集合内元素是该主机厂的任务集)
     * @param listListSupplierTask          按任务分开后的供应商服务集合
     * @param mapRelationshipMatrix         关系强度
     * @return                              匹配上的主机厂与供应商的交易契约
     */
    ArrayList<TransactionContract> getTransactionContracts(
            ArrayList<ArrayList<EngineFactoryManufacturingTask>> listListEngineFactoryTasks
            , ArrayList<ArrayList<SupplierTask>> listListSupplierTask
            , Map<String, Double> mapRelationshipMatrix);


}
