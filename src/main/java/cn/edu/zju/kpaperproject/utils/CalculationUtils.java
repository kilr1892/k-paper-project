package cn.edu.zju.kpaperproject.utils;


import cn.edu.zju.kpaperproject.enums.EngineFactoryEnum;
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
     * 需求预测
     * @param cycleTimes    循环次数
     * @param price         价格
     * @param quality       质量
     * @return 预测值
     */
    public static double demandForecast(int cycleTimes, int price, int quality) {
        int k1 = EngineFactoryEnum.engineFactoryDemandForecastInitK1;
        double k2 = EngineFactoryEnum.engineFactoryDemandForecastInitK2;
        int k1Step = EngineFactoryEnum.engineFactoryDemandForecastK1Step;
        double k2Step = EngineFactoryEnum.engineFactoryDemandForecastK2Step;

        k1 = k1 + (cycleTimes - 1) * k1Step;
        k2 = k2 + (cycleTimes - 1) * k2Step;

        return k1 - k2 * price / quality;
    }
}
