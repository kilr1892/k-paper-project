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
     * @return              最终交货结果集合
     */
    List<EngineFactoryFinalProvision> getListEngineFactoryFinalProvision(int cycleTimes, List<OrderPlus> listOrderPlus);
}
