package cn.edu.zju.kpaperproject.service.impl;

import cn.edu.zju.kpaperproject.enums.EngineFactoryEnum;
import cn.edu.zju.kpaperproject.enums.SupplierEnum;
import cn.edu.zju.kpaperproject.mapper.TbEngineFactoryDynamicMapper;
import cn.edu.zju.kpaperproject.mapper.TbEngineFactoryMapper;
import cn.edu.zju.kpaperproject.mapper.TbSupplierDynamicMapper;
import cn.edu.zju.kpaperproject.mapper.TbSupplierMapper;
import cn.edu.zju.kpaperproject.pojo.TbEngineFactory;
import cn.edu.zju.kpaperproject.pojo.TbEngineFactoryDynamic;
import cn.edu.zju.kpaperproject.pojo.TbSupplier;
import cn.edu.zju.kpaperproject.pojo.TbSupplierDynamic;
import cn.edu.zju.kpaperproject.service.InitService;
import cn.edu.zju.kpaperproject.utils.CommonUtils;
import cn.edu.zju.kpaperproject.utils.InitEngineFactoryUtils;
import cn.edu.zju.kpaperproject.utils.InitSupplierUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public void init(String experimentsNumber) {
        // 初始化 主机厂
        engineFactoryInit(experimentsNumber);
        // 初始化 供应商
        supplierInit(experimentsNumber);
        // 初始化 相关矩阵
        relationMatrixInit(experimentsNumber);
    }

    @Override
    public void engineFactoryInit(int experimentsNumber) {
        // TODO 要判断下是否已经实验过了
        // TODO 以后要改为批量插入数据库...
        TbEngineFactory tbEngineFactory = new TbEngineFactory();
        TbEngineFactoryDynamic tbEngineFactoryDynamic = new TbEngineFactoryDynamic();
        // 第几轮实验
        tbEngineFactory.setExperimentsNumber(experimentsNumber);
        // 0 代表初始化
        tbEngineFactory.setCycleTimes(0);
        tbEngineFactoryDynamic.setCycleTimes(0);

        for (int i = 0; i < EngineFactoryEnum.engineFactoryInitSum; i++) {
            // 工厂id
            String engineFactoryId = CommonUtils.genId();
            tbEngineFactory.setEngineFactoryId(engineFactoryId);
            // 地理位置
            int[] position = InitEngineFactoryUtils.initPosition();
            tbEngineFactory.setEngineFactoryLocationGX(position[0]);
            tbEngineFactory.setEngineFactoryLocationGY(position[1]);
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
            tbEngineFactoryDynamic.setEngineFactoryPricePL(price[0]);
            tbEngineFactoryDynamic.setEngineFactoryPricePU(price[1]);
            // 质量
            tbEngineFactoryDynamic.setEngineFactoryQualityQ(InitEngineFactoryUtils.initQuality());
            tbEngineFactoryDynamicMapper.insertSelective(tbEngineFactoryDynamic);
        }

    }

    @Override
    public void supplierInit(int experimentsNumber) {
        TbSupplier tbSupplier = new TbSupplier();
        tbSupplier.setExperimentsNumber(experimentsNumber);
        tbSupplier.setCycleTimes(0);
        TbSupplierDynamic tbSupplierDynamic = new TbSupplierDynamic();

        for (int i = 0; i < SupplierEnum.supplierInit210; i++) {
            supplierInit(SupplierEnum.supplierType210, tbSupplier, tbSupplierDynamic);
            tbSupplierMapper.insertSelective(tbSupplier);
        }
    }

    public void supplierInit(int typeCode, TbSupplier tbSupplier, TbSupplierDynamic tbSupplierDynamic) {
        // 供应商id
        String supplierId = CommonUtils.genId();
        tbSupplier.setSupplierId(supplierId);
        // 地理位置
        int[] position = InitSupplierUtils.initPosition();
        tbSupplier.setSupplierLocationGX(position[0]);
        tbSupplier.setSupplierLocationGY(position[1]);
        // 供应商代码
        String type = InitSupplierUtils.initType(typeCode);
        tbSupplier.setSupplierType(typeCode);
        // 每阶段固定成本
        int fixedCost = InitSupplierUtils.initFixedCost();

        // 动态数据----------------
        // 初始总资产
        tbSupplierDynamic.setSupplierTotalAssetsP(InitSupplierUtils.initTotalAssets());

        // 信誉度
        double credit = InitSupplierUtils.initCredit();
        // 最大产能
        int capacity = InitSupplierUtils.initCapacity();
        // 价格
        int[] price = InitSupplierUtils.initPrice();
        // 质量
        int quality = InitSupplierUtils.initQuality();
    }

    @Override
    public void relationMatrixInit(String experimentsNumber) {

    }
}
