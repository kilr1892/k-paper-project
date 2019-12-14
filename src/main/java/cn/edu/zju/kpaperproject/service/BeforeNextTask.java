package cn.edu.zju.kpaperproject.service;

import cn.edu.zju.kpaperproject.dto.EngineFactoryFinalProvision;
import cn.edu.zju.kpaperproject.dto.OrderPlus;

import java.util.List;

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
     * <p>
     * 对主机厂动态数据及服务商动态数据重算
     *
     * @param listEngineFactoryFinalProvisions 最终交货结果集合
     * @param listOrderPlus                    实际交易结果集合
     * @param listEngineFactoryDynamic         所有存活主机厂动态数据的集合
     * @param listSupplierDynamics             所有存活服务商动态数据集合
     */
//    void beforeNextTask(
//            List<EngineFactoryFinalProvision> listEngineFactoryFinalProvisions
//            , List<OrderPlus> listOrderPlus
//            , ArrayList<TransactionContract> listTransactionContract
//            , List<TbEngineFactory> listEngineFactory
//            , List<TbEngineFactoryDynamic> listEngineFactoryDynamic
//            , List<TbSupplier> listSupplier
//            , List<TbSupplierDynamic> listSupplierDynamics);
}
