package cn.edu.zju.kpaperproject.enums;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
@Component
@PropertySource("classpath:/config/experiments.properties")
public class EngineFactoryEnum {
    public static int engineFactoryInitSum;


    public static int engineFactoryLocationLow;

    public static int engineFactoryLocationUpper;

    public static int engineFactoryInitTotalAssetsLow;

    public static int engineFactoryInitTotalAssetsUpper;

    public static int engineFactoryFixedCost;

    public static double engineFactoryInitCredit;

    public static int engineFactoryInitCapacityLow;

    public static int engineFactoryInitCapacityUpper;
    public static int engineFactoryInitPriceLow;
    public static int engineFactoryInitPriceUpper;

    public static int engineFactoryInitQualityLow;
    public static int engineFactoryInitQualityUpper;


    public static int engineFactoryDemandForecastInitK1;
    public static double engineFactoryDemandForecastInitK2;
    public static int engineFactoryDemandForecastK1Step;
    public static double engineFactoryDemandForecastK2Step;

    @Value("${experiments.engineFactory.initSum}")
    public void setEngineFactoryInitSum(int engineFactoryInitSum) {
        EngineFactoryEnum.engineFactoryInitSum = engineFactoryInitSum;
    }
    @Value("${experiments.engineFactory.location.low}")
    public  void setEngineFactoryLocationLow(int engineFactoryLocationLow) {
        EngineFactoryEnum.engineFactoryLocationLow = engineFactoryLocationLow;
    }
    @Value("${experiments.engineFactory.location.upper}")
    public  void setEngineFactoryLocationUpper(int engineFactoryLocationUpper) {
        EngineFactoryEnum.engineFactoryLocationUpper = engineFactoryLocationUpper;
    }
    @Value("${experiments.engineFactory.initTotalAssets.low}")
    public  void setEngineFactoryInitTotalAssetsLow(int engineFactoryInitTotalAssetsLow) {
        EngineFactoryEnum.engineFactoryInitTotalAssetsLow = engineFactoryInitTotalAssetsLow;
    }
    @Value("${experiments.engineFactory.initTotalAssets.upper}")
    public  void setEngineFactoryInitTotalAssetsUpper(int engineFactoryInitTotalAssetsUpper) {
        EngineFactoryEnum.engineFactoryInitTotalAssetsUpper = engineFactoryInitTotalAssetsUpper;
    }
    @Value("${experiments.engineFactory.fixedCost}")
    public  void setEngineFactoryFixedCost(int engineFactoryFixedCost) {
        EngineFactoryEnum.engineFactoryFixedCost = engineFactoryFixedCost;
    }
    @Value("${experiments.engineFactory.initCredit}")
    public  void setEngineFactoryInitCredit(double engineFactoryInitCredit) {
        EngineFactoryEnum.engineFactoryInitCredit = engineFactoryInitCredit;
    }
    @Value("${experiments.engineFactory.initCapacity.low}")
    public  void setEngineFactoryInitCapacityLow(int engineFactoryInitCapacityLow) {
        EngineFactoryEnum.engineFactoryInitCapacityLow = engineFactoryInitCapacityLow;
    }
    @Value("${experiments.engineFactory.initCapacity.upper}")
    public  void setEngineFactoryInitCapacityUpper(int engineFactoryInitCapacityUpper) {
        EngineFactoryEnum.engineFactoryInitCapacityUpper = engineFactoryInitCapacityUpper;
    }
    @Value("${experiments.engineFactory.initPrice.low}")
    public  void setEngineFactoryInitPriceLow(int engineFactoryInitPriceLow) {
        EngineFactoryEnum.engineFactoryInitPriceLow = engineFactoryInitPriceLow;
    }
    @Value("${experiments.engineFactory.initPrice.upper}")
    public  void setEngineFactoryInitPriceUpper(int engineFactoryInitPriceUpper) {
        EngineFactoryEnum.engineFactoryInitPriceUpper = engineFactoryInitPriceUpper;
    }
    @Value("${experiments.engineFactory.quality.low}")
    public  void setEngineFactoryInitQualityLow(int engineFactoryInitQualityLow) {
        EngineFactoryEnum.engineFactoryInitQualityLow = engineFactoryInitQualityLow;
    }
    @Value("${experiments.engineFactory.quality.upper}")
    public  void setEngineFactoryInitQualityUpper(int engineFactoryInitQualityUpper) {
        EngineFactoryEnum.engineFactoryInitQualityUpper = engineFactoryInitQualityUpper;
    }
    @Value("${experiments.engineFactory.DemandForecast.initK1}")
    public  void setEngineFactoryDemandForecastInitK1(int engineFactoryDemandForecastInitK1) {
        EngineFactoryEnum.engineFactoryDemandForecastInitK1 = engineFactoryDemandForecastInitK1;
    }
    @Value("${experiments.engineFactory.DemandForecast.initK2}")
    public  void setEngineFactoryDemandForecastInitK2(double engineFactoryDemandForecastInitK2) {
        EngineFactoryEnum.engineFactoryDemandForecastInitK2 = engineFactoryDemandForecastInitK2;
    }
    @Value("${experiments.engineFactory.DemandForecast.K1.step}")
    public  void setEngineFactoryDemandForecastK1Step(int engineFactoryDemandForecastK1Step) {
        EngineFactoryEnum.engineFactoryDemandForecastK1Step = engineFactoryDemandForecastK1Step;
    }
    @Value("${experiments.engineFactory.DemandForecast.K2.step}")
    public  void setEngineFactoryDemandForecastK2Step(double engineFactoryDemandForecastK2Step) {
        EngineFactoryEnum.engineFactoryDemandForecastK2Step = engineFactoryDemandForecastK2Step;
    }
}
