package cn.edu.zju.kpaperproject.service.impl;

import cn.edu.zju.kpaperproject.dto.EngineFactoryFinalProvision;
import cn.edu.zju.kpaperproject.dto.OrderPlus;
import cn.edu.zju.kpaperproject.enums.CalculationEnum;
import cn.edu.zju.kpaperproject.enums.EngineFactoryEnum;
import cn.edu.zju.kpaperproject.service.BeforeNextTask;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
public class BeforeNextTaskImpl implements BeforeNextTask {

    // TODO 有没有循环次数, 实验次数的要求, 之后再说, 先把主要逻辑搞好

    public List<EngineFactoryFinalProvision> beforeNextTask(int cycleTimes, List<OrderPlus> listOrderPlus) {

        List<EngineFactoryFinalProvision> listRes = new ArrayList<>();
        // TODO List<OrderPlus>应该是按顺序(且已经按信誉度高的开始)存的(需要测试下, 不行就改...)

        EngineFactoryFinalProvision engineFactoryFinalProvision = null;

        // tmp实际成交数量
        int actualNumber = 0;
        // tmp实际价格
        int actualPrice = 0;
        int tmpPrice = 0;
        // tmp实际质量
        int actualQuality = 0;
        for (int i = 0; i < listOrderPlus.size(); i++) {
            OrderPlus orderPlus = listOrderPlus.get(i);


            int tmp = i % 5;
            if (tmp == 0) {
                // 实例化
                engineFactoryFinalProvision = new EngineFactoryFinalProvision();
                // 主机厂Id
                engineFactoryFinalProvision.setEngineFactoryId(orderPlus.getEngineFactoryId());
                // 实际数量
                actualNumber = orderPlus.getSupplierActualNumberM();
                // 最终质量
                actualQuality = 0;
                // 最终面向市场价格
                int[] engineFactory2ServiceOfferPrice = orderPlus.getEngineFactory2ServiceOfferPrice();
                actualPrice = RandomUtils.nextInt(engineFactory2ServiceOfferPrice[0], engineFactory2ServiceOfferPrice[1] + 1);
                tmpPrice = orderPlus.getSupplierActualPriceP();

            }

            // 最终产品数量
            actualNumber = Math.min(orderPlus.getSupplierActualNumberM(), actualNumber);
            // 最终面向市场价格
            tmpPrice += orderPlus.getSupplierActualPriceP();
            // 最终质量
            actualQuality += orderPlus.getSupplierActualQualityQs();

            if (tmp == 4) {
                // 最终产品数量
                engineFactoryFinalProvision.setFinalProductNumber(actualNumber);
                // 最终面向市场价格
                actualPrice = Math.min(actualPrice, tmpPrice);

                // 最终质量
                actualQuality = actualQuality / 5;
                engineFactoryFinalProvision.setFinalMarketQuality(actualQuality);
                // 市场需求量
                engineFactoryFinalProvision.setMarketNeedNumber(calMarketNeedNumber(cycleTimes));

                // 加入返回值数组中
                listRes.add(engineFactoryFinalProvision);
            }
        }
        return listRes;
    }

    /**
     * 计算市场需求
     *
     * @param cycleTimes 循环次数
     * @return 市场需求量
     */
    public int calMarketNeedNumber(int cycleTimes) {
        int k1 = EngineFactoryEnum.engineFactoryDemandForecastInitK1;
        double k2 = EngineFactoryEnum.engineFactoryDemandForecastInitK2;
        int k1Step = EngineFactoryEnum.engineFactoryDemandForecastK1Step;
        double k2Step = EngineFactoryEnum.engineFactoryDemandForecastK2Step;
        int cm = CalculationEnum.saleProductsInitCm;
        k1 = k1 + cycleTimes * k1Step;
        k2 = k2 + cycleTimes * k2Step;
        cm = cm + cycleTimes;

        int pa = RandomUtils.nextInt(EngineFactoryEnum.engineFactoryInitPriceLow, EngineFactoryEnum.engineFactoryInitPriceUpper + 1);
        int qa = RandomUtils.nextInt(EngineFactoryEnum.engineFactoryInitQualityLow, EngineFactoryEnum.engineFactoryInitQualityUpper + 1);
        return (int) Math.round((k1 - k2 * pa / qa) * cm);
    }
}
