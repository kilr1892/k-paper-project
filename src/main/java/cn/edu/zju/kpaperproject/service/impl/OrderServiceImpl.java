package cn.edu.zju.kpaperproject.service.impl;

import cn.edu.zju.kpaperproject.dto.EngineFactoryManufacturingTask;
import cn.edu.zju.kpaperproject.mapper.TbEngineFactoryDynamicMapper;
import cn.edu.zju.kpaperproject.pojo.TbEngineFactoryDynamic;
import cn.edu.zju.kpaperproject.pojo.TbEngineFactoryDynamicExample;
import cn.edu.zju.kpaperproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TbEngineFactoryDynamicMapper tbEngineFactoryDynamicMapper;

    @Override
    public ArrayList<EngineFactoryManufacturingTask> genTask(int cycleTimes) {
        ArrayList<EngineFactoryManufacturingTask> listEngineFactoryManufacturingTask = new ArrayList<>();



        // 任务类型
        // 需求
        // 期望价格区间
        // 期望质量


    }

    /**
     * 主机厂的计划市场需求量(计划产量)
     * @param cycleTimes        循环次数
     * @param engineFactoryId   主机厂id
     * @return                  计划产量
     */
    private int engineFactoryPlannedCapacity(int cycleTimes, String engineFactoryId) {
        TbEngineFactoryDynamicExample tbEngineFactoryDynamicExample = new TbEngineFactoryDynamicExample();
        TbEngineFactoryDynamicExample.Criteria criteria = tbEngineFactoryDynamicExample.createCriteria();
        // 第1次循环用第0次的预测产能
        criteria.andCycleTimesEqualTo(cycleTimes - 1);
        criteria.andEngineFactoryIdEqualTo(engineFactoryId);

        TbEngineFactoryDynamic tbEngineFactoryDynamic = tbEngineFactoryDynamicMapper.selectByExample(tbEngineFactoryDynamicExample).get(0);
        return Math.min(tbEngineFactoryDynamic.getEngineFactoryCapacityM(), tbEngineFactoryDynamic.getEngineFactoryDemandForecastD());
    }

}
