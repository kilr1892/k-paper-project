package cn.edu.zju.kpaperproject.utils;


import cn.edu.zju.kpaperproject.enums.EngineFactoryEnum;
import cn.edu.zju.kpaperproject.enums.NumberEnum;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

/**
 * 与计算相关.
 *
 * @author RichardLee
 * @version v1.0
 */
@Component
public class CalculationUtils {

    /**
     * 需求预测(在每次循环结束后, 预测下一阶段)
     * 第1次循环, 用的是第0次的数据
     * @param cycleTimes    循环次数
     * @param priceLow      价格下限
     * @param priceUpper    价格上限
     * @param quality       质量
     * @return 预测值
     */
    public static int demandForecast(int cycleTimes, int priceLow,int priceUpper, int quality) {
        int k1 = EngineFactoryEnum.engineFactoryDemandForecastInitK1;
        double k2 = EngineFactoryEnum.engineFactoryDemandForecastInitK2;
        int k1Step = EngineFactoryEnum.engineFactoryDemandForecastK1Step;
        double k2Step = EngineFactoryEnum.engineFactoryDemandForecastK2Step;

        k1 = k1 + cycleTimes * k1Step;
        k2 = k2 + cycleTimes  * k2Step;

        int price = RandomUtils.nextInt(priceLow, priceUpper + NumberEnum.QUALITY_STEP);

        return (int)Math.round(k1 - k2 * price / quality);
    }


}
