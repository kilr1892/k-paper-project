package cn.edu.zju.kpaperproject.service.impl;

import cn.edu.zju.kpaperproject.dto.EngineFactoryManufacturingTask;
import cn.edu.zju.kpaperproject.enums.NumberEnum;
import cn.edu.zju.kpaperproject.enums.SupplierEnum;
import cn.edu.zju.kpaperproject.enums.TaskDecompositionEnum;
import cn.edu.zju.kpaperproject.mapper.TbEngineFactoryDynamicMapper;
import cn.edu.zju.kpaperproject.mapper.TbEngineFactoryMapper;
import cn.edu.zju.kpaperproject.pojo.*;
import cn.edu.zju.kpaperproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TbEngineFactoryMapper tbEngineFactoryMapper;
    @Autowired
    private TbEngineFactoryDynamicMapper tbEngineFactoryDynamicMapper;

    /**
     * 生成主机厂任务
     *
     * @return 每个主机厂的任务
     */
    @Override
    public ArrayList<EngineFactoryManufacturingTask> genTask(int cycleTimes) {
        ArrayList<EngineFactoryManufacturingTask> listEngineFactoryManufacturingTask = new ArrayList<>();
        // 找出所有存活的主机厂
        List<TbEngineFactory> tbEngineFactories = getListEngineFactoryWithAlive();
        // 服务代码数组
        int[] supplierTypeCodes = SupplierEnum.getSupplierTypeCodes();

        for (TbEngineFactory aEngineFactory : tbEngineFactories) {
            String engineFactoryId = aEngineFactory.getEngineFactoryId();
            // 获取主机厂动态数据模型
            TbEngineFactoryDynamic engineFactoryDynamic = getEngineFactoryDynamicWithCycleTimeAndEngineFactoryId(cycleTimes, engineFactoryId);
            // 成品数量预测
            int qi = genEngineFactoryPlannedCapacity(cycleTimes, engineFactoryId);

            for (int i = 0; i < supplierTypeCodes.length; i++) {
                // 任务分解模型实例
                EngineFactoryManufacturingTask engineFactoryManufacturingTask = new EngineFactoryManufacturingTask();

                // 主机厂id
                engineFactoryManufacturingTask.setEngineFactoryId(engineFactoryId);
                // 任务类型
                engineFactoryManufacturingTask.setTaskType(supplierTypeCodes[i]);
                // 服务需求量
                engineFactoryManufacturingTask.setEngineFactoryNeedServiceNumber(qi);
                // 期望价格区间
                int[] engineFactory2ServiceOfferPrice = genEngineFactory2ServiceOfferPrice(engineFactoryDynamic, i);
                engineFactoryManufacturingTask.setEngineFactory2ServiceOfferPrice(engineFactory2ServiceOfferPrice);
                // 期望质量
                engineFactoryManufacturingTask.setEngineFactoryExpectedQuality(genEngineFactoryQuality(engineFactoryDynamic));

                listEngineFactoryManufacturingTask.add(engineFactoryManufacturingTask);
            }
        }
        return listEngineFactoryManufacturingTask;

    }

    private List<TbEngineFactory> getListEngineFactoryWithAlive() {
        TbEngineFactoryExample tbEngineFactoryExample = new TbEngineFactoryExample();
        TbEngineFactoryExample.Criteria criteria = tbEngineFactoryExample.createCriteria();
        criteria.andEngineFactoryAliveEqualTo(true);
        return tbEngineFactoryMapper.selectByExample(tbEngineFactoryExample);
    }


    /**
     * 主机厂的计划市场需求量(计划产量)
     *
     * @param cycleTimes      循环次数
     * @param engineFactoryId 主机厂id
     * @return 计划产量
     */
    private int genEngineFactoryPlannedCapacity(int cycleTimes, String engineFactoryId) {
        TbEngineFactoryDynamicExample tbEngineFactoryDynamicExample = new TbEngineFactoryDynamicExample();
        TbEngineFactoryDynamicExample.Criteria criteria = tbEngineFactoryDynamicExample.createCriteria();
        // 第1次循环用第0次的预测产能
        criteria.andCycleTimesEqualTo(cycleTimes - 1);
        criteria.andEngineFactoryIdEqualTo(engineFactoryId);

        TbEngineFactoryDynamic tbEngineFactoryDynamic = tbEngineFactoryDynamicMapper.selectByExample(tbEngineFactoryDynamicExample).get(0);
        return Math.min(tbEngineFactoryDynamic.getEngineFactoryCapacityM(), tbEngineFactoryDynamic.getEngineFactoryDemandForecastD());
    }

    /**
     * 计算各服务期望的价格区间
     *
     * @param tbEngineFactoryDynamic 主机厂动态数据模型
     * @param codeType               服务代码序号,0 代表210....4代表250
     * @return 价格区间
     */
    private int[] genEngineFactory2ServiceOfferPrice(TbEngineFactoryDynamic tbEngineFactoryDynamic, int codeType) {
        int[] res = new int[2];
        double[] taskDecompositionEjcs = TaskDecompositionEnum.getTaskDecompositionEjcs();

        int[] priceRange = getEngineFactoryExceptPriceRange(tbEngineFactoryDynamic);

        for (int i = 0; i < priceRange.length; i++) {
            int round = (int) Math.round(priceRange[i] * TaskDecompositionEnum.taskDecompositionXic * taskDecompositionEjcs[codeType] / TaskDecompositionEnum.taskDecompositionEjd);
            res[i] = round;
        }
        return res;
    }

    /**
     * 获取主机厂的期待整机售价
     *
     * @param tbEngineFactoryDynamic 主机厂动态数据模型
     * @return 期待售价价格范围
     */
    private int[] getEngineFactoryExceptPriceRange(TbEngineFactoryDynamic tbEngineFactoryDynamic) {

        return new int[]{tbEngineFactoryDynamic.getEngineFactoryPricePL(), tbEngineFactoryDynamic.getEngineFactoryPricePU()};
    }

    /**
     * 通过循环次数和id, 获取主机厂动态数据模型
     *
     * @param cycleTime       循环次数(从1开始)
     * @param engineFactoryId 主机厂id
     * @return 主机厂动态数据模型
     */
    private TbEngineFactoryDynamic getEngineFactoryDynamicWithCycleTimeAndEngineFactoryId(int cycleTime, String engineFactoryId) {
        TbEngineFactoryDynamicExample tbEngineFactoryDynamicExample = new TbEngineFactoryDynamicExample();
        TbEngineFactoryDynamicExample.Criteria criteria = tbEngineFactoryDynamicExample.createCriteria();
        // 都是用上一个阶段的数值
        criteria.andCycleTimesEqualTo(cycleTime - 1);
        criteria.andEngineFactoryIdEqualTo(engineFactoryId);
        return tbEngineFactoryDynamicMapper.selectByExample(tbEngineFactoryDynamicExample).get(0);
    }

    /**
     * 计算任务期望质量
     *
     * @param tbEngineFactoryDynamic 主机厂动态数据模型
     * @return 分解任务期望质量
     */
    private int genEngineFactoryQuality(TbEngineFactoryDynamic tbEngineFactoryDynamic) {
        int engineFactoryQuality = tbEngineFactoryDynamic.getEngineFactoryQualityQ();
        return engineFactoryQuality >= NumberEnum.QUALITY_UPPER ? NumberEnum.QUALITY_UPPER : engineFactoryQuality + NumberEnum.NUMBER_1;
    }
}
