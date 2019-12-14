package cn.edu.zju.kpaperproject.service;

import cn.edu.zju.kpaperproject.dto.EngineFactoryFinalProvision;
import cn.edu.zju.kpaperproject.dto.OrderPlus;
import cn.edu.zju.kpaperproject.dto.TransactionContract;
import cn.edu.zju.kpaperproject.pojo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 下一个循环开始前的准备.
 *
 * @author RichardLee
 * @version v1.0
 */
public interface BeforeNextTask {
    /**
     * 生成最终的交货结果
     *
     * @param cycleTimes    实验次数
     * @param listOrderPlus 产品订单
     * @return 最终交货结果集合
     */
    List<EngineFactoryFinalProvision> getListEngineFactoryFinalProvision(int cycleTimes, List<OrderPlus> listOrderPlus);

    /**
     * 计算总资产
     * // 计算主机厂的产能利用率
     * // 主机厂修改服务质量-售价-产能
     * // 计算供应商的产能利用率
     * // 供应商修改服务质量-售价-产能
     * // 企业进入与退出
     * // 双方信誉度归一化
     * <p>
     * 对主机厂动态数据及服务商动态数据重算
     *
     * @param experimentsNumber                          实验次数
     * @param cycleTime                                  循环次数
     * @param listEngineFactoryFinalProvisions           最终交货结果集合
     * @param listOrderPlus                              实际交易结果集合
     * @param listTransactionContract                    交易契约集合
     * @param listEngineFactory                          主机厂集合
     * @param listEngineFactoryDynamic                   主机厂动态数据的集合
     * @param listSupplier                               供应商集合
     * @param listSupplierDynamics                       所有存活服务商动态数据集合
     * @param mapRelationshipMatrix2WithTbRelationMatrix 关系矩阵
     */
    void beforeNextTask(
            int experimentsNumber
            , int cycleTime
            , List<EngineFactoryFinalProvision> listEngineFactoryFinalProvisions
            , List<OrderPlus> listOrderPlus
            , ArrayList<TransactionContract> listTransactionContract
            , List<TbEngineFactory> listEngineFactory
            , List<TbEngineFactoryDynamic> listEngineFactoryDynamic
            , List<TbSupplier> listSupplier
            , List<TbSupplierDynamic> listSupplierDynamics
            , Map<String, TbRelationMatrix> mapRelationshipMatrix2WithTbRelationMatrix);
}
