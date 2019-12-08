package cn.edu.zju.kpaperproject.enums;

import org.springframework.beans.factory.annotation.Value;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
public class EngineFactoryEnum {

    @Value("${experiments.engineFactory.initSum}")
    public static int engineFactoryInitSum;

    @Value("${experiments.engineFactory.location.low}")
    public static int engineFactoryLocationLow;

    @Value("${experiments.engineFactory.location.upper}")
    public static int engineFactoryLocationUpper;

    @Value("${experiments.engineFactory.initTotalAssets.low}")
    public static int engineFactoryInitTotalAssetsLow;

    @Value("${experiments.engineFactory.initTotalAssets.upper}")
    public static int engineFactoryInitTotalAssetsUpper;

    @Value("${experiments.engineFactory.fixedCost}")
    public static int engineFactoryFixedCost;

    @Value("${experiments.engineFactory.initCredit}")
    public static double engineFactoryInitCredit;

    @Value("${experiments.engineFactory.initCapacity.low}")
    public static int engineFactoryInitCapacityLow;

    @Value("${experiments.engineFactory.initCapacity.upper}")
    public static int engineFactoryInitCapacityUpper;
    @Value("${experiments.engineFactory.initPrice.low}")
    public static int engineFactoryInitPriceLow;
    @Value("${experiments.engineFactory.initPrice.upper}")
    public static int engineFactoryInitPriceUpper;

    @Value("${experiments.engineFactory.quality.low}")
    public static int engineFactoryInitQualityLow;
    @Value("${experiments.engineFactory.quality.upper}")
    public static int engineFactoryInitQualityUpper;


    @Value("${experiments.engineFactory.DemandForecast.initK1}")
    public static int engineFactoryDemandForecastInitK1;
    @Value("${experiments.engineFactory.DemandForecast.initK2}")
    public static double engineFactoryDemandForecastInitK2;
    @Value("${experiments.engineFactory.DemandForecast.K1.step}")
    public static int engineFactoryDemandForecastK1Step;
    @Value("${experiments.engineFactory.DemandForecast.K2.step}")
    public static double engineFactoryDemandForecastK2Step;


}
