package cn.edu.zju.kpaperproject.service.impl;

import cn.edu.zju.kpaperproject.dto.EngineFactoryManufacturingTask;
import cn.edu.zju.kpaperproject.dto.SupplierTask;
import cn.edu.zju.kpaperproject.enums.NumberEnum;
import cn.edu.zju.kpaperproject.enums.SupplierEnum;
import cn.edu.zju.kpaperproject.enums.TaskDecompositionEnum;
import cn.edu.zju.kpaperproject.mapper.*;
import cn.edu.zju.kpaperproject.pojo.*;
import cn.edu.zju.kpaperproject.service.StartTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
@Service
public class StartTaskServiceImpl implements StartTaskService {

    @Autowired
    private TbEngineFactoryMapper tbEngineFactoryMapper;
    @Autowired
    private TbEngineFactoryDynamicMapper tbEngineFactoryDynamicMapper;

    @Autowired
    private TbSupplierMapper tbSupplierMapper;
    @Autowired
    private TbSupplierDynamicMapper tbSupplierDynamicMapper;

    @Autowired
    private TbRelationMatrixMapper tbRelationMatrixMapper;

    /**
     * 获得主机厂与供应商之间的关系矩阵
     * key   = 主机厂id + 供应商id
     * value = 两者关系强度
     *
     * @param experimentsNumber 实验次数
     * @param cycleTime         循环次数
     * @return                  循环次数 - 1 时(最新的)关系矩阵
     */
    @Override
    public Map<String, Double> getMapRelationshipMatrix(int experimentsNumber, int cycleTime) {
        Map<String, Double> mapRes = new HashMap<>(1000);

        // 查询所有的可用的关系数据
        TbRelationMatrixExample tbRelationMatrixExample = new TbRelationMatrixExample();
        TbRelationMatrixExample.Criteria criteria = tbRelationMatrixExample.createCriteria();
        criteria.andExperimentsNumberEqualTo(experimentsNumber);
        criteria.andCycleTimesEqualTo(cycleTime - 1);
        criteria.andRelationMatrixAliveEqualTo(true);
        List<TbRelationMatrix> tbRelationMatrices = tbRelationMatrixMapper.selectByExample(tbRelationMatrixExample);

        for (TbRelationMatrix aRelationMatrix : tbRelationMatrices) {
            mapRes.put(aRelationMatrix.getMapKey(), aRelationMatrix.getRelationScore());
        }

        return mapRes;
    }
    /**
     * 获得主机厂与供应商之间的关系矩阵2
     * key   = 主机厂id + 供应商id
     * value = TbRelationMatrix
     *
     * @param experimentsNumber 实验次数
     * @param cycleTime         循环次数
     * @return                  循环次数 - 1 时(最新的)关系矩阵
     */
    @Override
    public Map<String, TbRelationMatrix> getMapRelationshipMatrix2WithTbRelationMatrix(int experimentsNumber, int cycleTime) {
        Map<String, TbRelationMatrix> mapRes = new HashMap<>(1000);

        // 查询所有的可用的关系数据
        TbRelationMatrixExample tbRelationMatrixExample = new TbRelationMatrixExample();
        TbRelationMatrixExample.Criteria criteria = tbRelationMatrixExample.createCriteria();
        criteria.andExperimentsNumberEqualTo(experimentsNumber);
        criteria.andCycleTimesEqualTo(cycleTime - 1);
        criteria.andRelationMatrixAliveEqualTo(true);
        List<TbRelationMatrix> tbRelationMatrices = tbRelationMatrixMapper.selectByExample(tbRelationMatrixExample);

        for (TbRelationMatrix aRelationMatrix : tbRelationMatrices) {
            mapRes.put(aRelationMatrix.getMapKey(), aRelationMatrix);
        }

        return mapRes;
    }
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
    @Override
    public ArrayList<ArrayList<SupplierTask>> genSupplierTask(int experimentsNumber, int cycleTime) {
        // 返回值, 索引0~4 就是210~205的集合
        ArrayList<ArrayList<SupplierTask>> res = new ArrayList<>(5);
        int resSize = 5;
        for (int i = 0; i < resSize; i++) {
            res.add(new ArrayList<>());
        }

        // 查询出所有活着的供应商
        TbSupplierExample tbSupplierExample = new TbSupplierExample();
        TbSupplierExample.Criteria tbSupplierExampleCriteria = tbSupplierExample.createCriteria();
        tbSupplierExampleCriteria.andExperimentsNumberEqualTo(experimentsNumber);
        tbSupplierExampleCriteria.andSupplierAliveEqualTo(true);
        List<TbSupplier> suppliers = tbSupplierMapper.selectByExample(tbSupplierExample);

        for (TbSupplier aSupplier : suppliers) {
            // 供应商id
            String supplierId = aSupplier.getSupplierId();

            // 获取供应商动态数据
            TbSupplierDynamicExample tbSupplierDynamicExample = new TbSupplierDynamicExample();
            TbSupplierDynamicExample.Criteria criteria = tbSupplierDynamicExample.createCriteria();
            // 都是用上一个阶段的数值
            criteria.andCycleTimesEqualTo(cycleTime - 1);
            criteria.andSupplierIdEqualTo(supplierId);
            TbSupplierDynamic tbSupplierDynamic = tbSupplierDynamicMapper.selectByExample(tbSupplierDynamicExample).get(0);

            // ___需要存起来的供应商任务模型
            SupplierTask supplierTask = new SupplierTask();
            // 供应商id
            supplierTask.setSupplierId(supplierId);
            /** 供应商信誉度 */
//            private double supplierCredit;
            supplierTask.setSupplierCredit(tbSupplierDynamic.getSupplierCreditA());
            // 服务类型
            int supplierType = aSupplier.getSupplierType();
            supplierTask.setSupplierType(supplierType);
            // 服务质量
            supplierTask.setSupplierQuality(tbSupplierDynamic.getSupplierQualityQs());
            // 服务价格
            supplierTask.setSupplierPriceRange(new int[]{tbSupplierDynamic.getSupplierPricePL(), tbSupplierDynamic.getSupplierPricePU()});
            // 服务产能
            supplierTask.setSupplierCapacity(tbSupplierDynamic.getSupplierCapacityM());
            // 地理位置
            supplierTask.setSupplierLocationXY(new int[]{aSupplier.getSupplierLocationGX(), aSupplier.getSupplierLocationGY()});
            // 剩余产能
            supplierTask.setSupplierRestCapacity(tbSupplierDynamic.getSupplierCapacityM());

            // 选择对应点的集合存入供应商任务模型
            switch (supplierType) {
                case 210:
                    res.get(0).add(supplierTask);
                    break;
                case 220:
                    res.get(1).add(supplierTask);
                    break;
                case 230:
                    res.get(2).add(supplierTask);
                    break;
                case 240:
                    res.get(3).add(supplierTask);
                    break;
                case 250:
                    res.get(4).add(supplierTask);
                    break;
                default:
                    throw new RuntimeException("供应商类型错误");
            }
        }

        return res;
    }

    /**
     * 生成主机厂分解任务
     * <p>
     * 返回值按信誉度从高到底排, 信誉度相同就按210任务出价从高到底排
     *
     * @param experimentsNumber 实验次数
     * @param cycleTime         循环的次数, 从1开始
     * @return                  返回值中每个元素代表一个主机厂分解的任务集
     */
    @Override
    public ArrayList<ArrayList<EngineFactoryManufacturingTask>> genEngineFactoryTaskDecomposition(int experimentsNumber, int cycleTime) {

        // 返回值, 排序
        ArrayList<ArrayList<EngineFactoryManufacturingTask>> res = new ArrayList<>();

        // 找出所有存活的主机厂
        List<TbEngineFactory> tbEngineFactories = getListEngineFactoryWithAlive(experimentsNumber);
        // 服务代码数组
        int[] supplierTypeCodes = SupplierEnum.getSupplierTypeCodes();

        for (TbEngineFactory aEngineFactory : tbEngineFactories) {
            // 主机厂静态数据aEngineFactory

            // 这个是每个主机厂的集合
            ArrayList<EngineFactoryManufacturingTask> listEngineFactoryManufacturingTask = new ArrayList<>();

            // 主机厂id
            String engineFactoryId = aEngineFactory.getEngineFactoryId();
            // 获取主机厂动态数据模型
            TbEngineFactoryDynamic engineFactoryDynamic = getEngineFactoryDynamicWithCycleTimeAndEngineFactoryId(cycleTime, engineFactoryId);
            // 信誉度
            Double engineFactoryCredit = engineFactoryDynamic.getEngineFactoryCreditH();
            // 成品数量预测
            int qi = genEngineFactoryPlannedCapacity(cycleTime, engineFactoryId);

            for (int i = 0; i < supplierTypeCodes.length; i++) {
                // 任务分解模型实例
                // 主机id + 信誉度 + 地理位置
                EngineFactoryManufacturingTask engineFactoryManufacturingTask = new EngineFactoryManufacturingTask(
                        engineFactoryId, engineFactoryCredit, new int[]{aEngineFactory.getEngineFactoryLocationGX(), aEngineFactory.getEngineFactoryLocationGY()});

                // 任务类型
                engineFactoryManufacturingTask.setTaskType(supplierTypeCodes[i]);
                // 服务需求量
                engineFactoryManufacturingTask.setEngineFactoryNeedServiceNumber(qi);
                // 期望价格区间
                int[] engineFactory2ServiceOfferPrice = genEngineFactory2ServiceOfferPrice(engineFactoryDynamic, i);
                engineFactoryManufacturingTask.setEngineFactory2ServiceOfferPrice(engineFactory2ServiceOfferPrice);
                // 期望质量
                engineFactoryManufacturingTask.setEngineFactoryExpectedQuality(genEngineFactoryQuality(engineFactoryDynamic));

                // listEngineFactoryManufacturingTask包含5个子任务
                listEngineFactoryManufacturingTask.add(engineFactoryManufacturingTask);
            }
            // 每个主机厂加入res中
            res.add(listEngineFactoryManufacturingTask);
        }
        // 返回值排序
        res.sort((o1, o2) -> {
            double diff = o2.get(0).getEngineFactoryCredit() - o1.get(0).getEngineFactoryCredit();
            if (diff != 0) {
                // 先按信誉度排
                return diff > 0 ? 1 : -1;
            } else {
                // 信誉度相等, 按出价高的排
                // 这里的出价高指的是210的出价
                return o2.get(0).getEngineFactory2ServiceOfferPrice()[1] - o1.get(0).getEngineFactory2ServiceOfferPrice()[1];
            }
        });
        return res;

    }

    /**
     * 获取所有存活的主机厂
     * @param experimentsNumber 第几次实验
     * @return                  主机厂静态数据集合
     */
    private List<TbEngineFactory> getListEngineFactoryWithAlive(int experimentsNumber) {
        TbEngineFactoryExample tbEngineFactoryExample = new TbEngineFactoryExample();
        TbEngineFactoryExample.Criteria criteria = tbEngineFactoryExample.createCriteria();
        criteria.andEngineFactoryAliveEqualTo(true);
        criteria.andExperimentsNumberEqualTo(experimentsNumber);
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
        return engineFactoryQuality >= NumberEnum.QUALITY_UPPER_LIMIT ? NumberEnum.QUALITY_UPPER_LIMIT : engineFactoryQuality + NumberEnum.QUALITY_STEP;
    }
}
