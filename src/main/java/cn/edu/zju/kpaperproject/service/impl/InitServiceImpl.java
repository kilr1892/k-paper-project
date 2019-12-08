package cn.edu.zju.kpaperproject.service.impl;

import cn.edu.zju.kpaperproject.enums.EngineFactoryEnum;
import cn.edu.zju.kpaperproject.enums.NumberEnum;
import cn.edu.zju.kpaperproject.enums.SupplierEnum;
import cn.edu.zju.kpaperproject.mapper.*;
import cn.edu.zju.kpaperproject.pojo.*;
import cn.edu.zju.kpaperproject.service.InitService;
import cn.edu.zju.kpaperproject.utils.CommonUtils;
import cn.edu.zju.kpaperproject.utils.InitEngineFactoryUtils;
import cn.edu.zju.kpaperproject.utils.InitRelationMatrixUtils;
import cn.edu.zju.kpaperproject.utils.InitSupplierUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InitServiceImpl implements InitService {

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


    @Override
    public void init(int experimentsNumber) {
        // 初始化 主机厂
        engineFactoryInit(experimentsNumber);
        // 初始化 供应商
        supplierInit(experimentsNumber);
        // 初始化 相关矩阵
        relationMatrixInit(experimentsNumber);
    }

    /**
     * 初始化主机厂
     * @param experimentsNumber 实验次数
     */
    private void engineFactoryInit(int experimentsNumber) {
        // TODO 要判断下是否已经实验过了
        // TODO 以后要改为批量插入数据库...
        TbEngineFactory tbEngineFactory = new TbEngineFactory();
        TbEngineFactoryDynamic tbEngineFactoryDynamic = new TbEngineFactoryDynamic();
        // 第几轮实验
        tbEngineFactory.setExperimentsNumber(experimentsNumber);
        // 0 代表初始化
        tbEngineFactory.setCycleTimes(NumberEnum.CYCLE_TIME_INIT);
        tbEngineFactoryDynamic.setCycleTimes(NumberEnum.CYCLE_TIME_INIT);

        for (int i = 0; i < EngineFactoryEnum.engineFactoryInitSum; i++) {
            // 工厂id
            String engineFactoryId = CommonUtils.genId();
            tbEngineFactory.setEngineFactoryId(engineFactoryId);
            // 地理位置
            int[] position = InitEngineFactoryUtils.initPosition();
            tbEngineFactory.setEngineFactoryLocationGX(position[NumberEnum.POSITION_X]);
            tbEngineFactory.setEngineFactoryLocationGY(position[NumberEnum.POSITION_Y]);
            // 每阶段固定成本
            tbEngineFactory.setEngineFactoryFixedCostC(InitEngineFactoryUtils.initFixedCost());
            // 存活
            tbEngineFactory.setEngineFactoryAlive(true);
            // 单条插入
            tbEngineFactoryMapper.insertSelective(tbEngineFactory);

            // 工厂动态数据------------------
            tbEngineFactoryDynamic.setEngineFactoryId(engineFactoryId);
            // 初始总资产
            tbEngineFactoryDynamic.setEngineFactoryTotalAssetsP(InitEngineFactoryUtils.initTotalAssets());
            // 信誉度
            tbEngineFactoryDynamic.setEngineFactoryCreditH(InitEngineFactoryUtils.initCredit());
            // 最大产能
            tbEngineFactoryDynamic.setEngineFactoryCapacityM(InitEngineFactoryUtils.initCapacity());
            // 价格
            int[] price = InitEngineFactoryUtils.initPrice();
            tbEngineFactoryDynamic.setEngineFactoryPricePL(price[NumberEnum.PRICE_LOW]);
            tbEngineFactoryDynamic.setEngineFactoryPricePU(price[NumberEnum.PRICE_UPPER]);
            // 质量
            tbEngineFactoryDynamic.setEngineFactoryQualityQ(InitEngineFactoryUtils.initQuality());
            tbEngineFactoryDynamicMapper.insertSelective(tbEngineFactoryDynamic);
        }

    }

    /**
     * 初始化供应商
     * @param experimentsNumber 实验次数
     */
    private void supplierInit(int experimentsNumber) {
        // TODO 以后要改为批量插入数据库...
        TbSupplier tbSupplier = new TbSupplier();
        tbSupplier.setExperimentsNumber(experimentsNumber);
        // 0
        tbSupplier.setCycleTimes(NumberEnum.CYCLE_TIME_INIT);
        TbSupplierDynamic tbSupplierDynamic = new TbSupplierDynamic();
        // 0
        tbSupplierDynamic.setCycleTimes(NumberEnum.CYCLE_TIME_INIT);

        int[] supplierTypeSum = {SupplierEnum.supplierInit210Sum, SupplierEnum.supplierInit220Sum, SupplierEnum.supplierInit230Sum
                , SupplierEnum.supplierInit240Sum, SupplierEnum.supplierInit250Sum};

        int[] supplierTypeCode = {SupplierEnum.supplierType210, SupplierEnum.supplierType220, SupplierEnum.supplierType230
                , SupplierEnum.supplierType240, SupplierEnum.supplierType250};

        // 循环5次
        for (int i = 0; i < supplierTypeSum.length; i++) {
            for (int j = 0; j < supplierTypeSum[i]; j++) {
                //
                supplierInit(supplierTypeCode[i], tbSupplier, tbSupplierDynamic);
                tbSupplierMapper.insertSelective(tbSupplier);
                tbSupplierDynamicMapper.insertSelective(tbSupplierDynamic);
            }
        }
    }

    /**
     * 生成对应的数据
     * @param typeCode          供应商代码
     * @param tbSupplier        同一个TbSupplier
     * @param tbSupplierDynamic 同一个TbSupplierDynamic
     */
    private void supplierInit(int typeCode, TbSupplier tbSupplier, TbSupplierDynamic tbSupplierDynamic) {
        // 供应商id
        String supplierId = CommonUtils.genId();
        tbSupplier.setSupplierId(supplierId);
        // 地理位置
        int[] position = InitSupplierUtils.initPosition();
        tbSupplier.setSupplierLocationGX(position[NumberEnum.POSITION_X]);
        tbSupplier.setSupplierLocationGY(position[NumberEnum.POSITION_Y]);
        // 供应商代码
        tbSupplier.setSupplierType(InitSupplierUtils.initType(typeCode));
        // 每阶段固定成本
        tbSupplier.setSupplierFixedCostC(InitSupplierUtils.initFixedCost());
        tbSupplier.setSupplierAlive(true);
        // 动态数据----------------
        tbSupplierDynamic.setCycleTimes(NumberEnum.CYCLE_TIME_INIT);
        // 供应商id
        tbSupplierDynamic.setSupplierId(supplierId);
        // 初始总资产
        tbSupplierDynamic.setSupplierTotalAssetsP(InitSupplierUtils.initTotalAssets());
        // 信誉度
        tbSupplierDynamic.setSupplierCreditA(InitSupplierUtils.initCredit());
        // 最大产能
        tbSupplierDynamic.setSupplierCapacityM(InitSupplierUtils.initCapacity());
        // 价格
        int[] price = InitSupplierUtils.initPrice();
        tbSupplierDynamic.setSupplierPricePL(price[NumberEnum.PRICE_LOW]);
        tbSupplierDynamic.setSupplierPricePU(price[NumberEnum.PRICE_UPPER]);
        // 质量
        tbSupplierDynamic.setSupplierQualityQs(InitSupplierUtils.initQuality());
        tbSupplierDynamic.setSupplierServiceAlive(true);
    }


    /**
     * 初始化关系矩阵
     * @param experimentsNumber 实验次数
     */
    private void relationMatrixInit(int experimentsNumber) {
        // TODO 以后要改为批量插入数据库...
        // 查出所有的主机厂, 供应商
        // 主机厂
        List<TbEngineFactory> tbEngineFactories = listInitEngineFactory(experimentsNumber);
        // 供应商
        List<TbSupplier> tbSuppliers = listInitSupplier(experimentsNumber);
        // 两个循环生成关系矩阵
        // 填充用的
        TbRelationMatrix tbRelationMatrix = new TbRelationMatrix();
        tbRelationMatrix.setExperimentsNumber(experimentsNumber);
        tbRelationMatrix.setCycleTimes(NumberEnum.CYCLE_TIME_INIT);
        for (TbEngineFactory aTbEngineFactory : tbEngineFactories) {
            tbRelationMatrix.setEngineFactoryId(aTbEngineFactory.getEngineFactoryId());
            for (TbSupplier aTbSupplier : tbSuppliers) {
                tbRelationMatrix.setSupplierId(aTbSupplier.getSupplierId());
                tbRelationMatrix.setRelationScore(InitRelationMatrixUtils.initRelationshipStrengthScore());
                tbRelationMatrixMapper.insertSelective(tbRelationMatrix);
            }
        }

    }

    /**
     * 初始化的主机厂
     * @param experimentsNumber 实验次数
     * @return                  所有初始的主机厂
     */
    private List<TbEngineFactory> listInitEngineFactory(int experimentsNumber) {
        TbEngineFactoryExample tbEngineFactoryExample = new TbEngineFactoryExample();
        TbEngineFactoryExample.Criteria engineFactoryExampleCriteria = tbEngineFactoryExample.createCriteria();
        engineFactoryExampleCriteria.andExperimentsNumberEqualTo(experimentsNumber);
        return tbEngineFactoryMapper.selectByExample(tbEngineFactoryExample);
    }
    /**
     * 初始化的供应商
     * @param experimentsNumber 实验次数
     * @return                  所有初始的供应商
     */
    private List<TbSupplier> listInitSupplier(int experimentsNumber) {
        TbSupplierExample tbSupplierExample = new TbSupplierExample();
        TbSupplierExample.Criteria supplierExampleCriteria = tbSupplierExample.createCriteria();
        supplierExampleCriteria.andExperimentsNumberEqualTo(experimentsNumber);
        return tbSupplierMapper.selectByExample(tbSupplierExample);
    }


}