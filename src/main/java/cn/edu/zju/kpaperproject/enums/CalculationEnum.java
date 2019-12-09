package cn.edu.zju.kpaperproject.enums;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 计算相关的枚举类.
 *
 * @author RichardLee
 * @version v1.0
 */
@Component
public class CalculationEnum {
    public static double weightFactorA3slash;
    public static double weightFactorB3slash;
    public static double weightFactorX3slash;
    public static double weightFactorY3slash;

    public static double relationshipStrengthInitLow;
    public static double relationshipStrengthInitUpper;
    public static int relationshipStrengthA2Slash;
    public static int relationshipStrengthNi;
    public static double relationshipStrengthRq;
    public static double relationshipStrengthRc;
    public static double apLm1;
    public static double apLm2;
    public static double apLm3;
    public static double apLm4;
    public static double apLm5;
    public static int freight;
    public static int saleProductsEjd;
    public static int saleProductsNj;
    public static double saleProductsCp;
    public static int saleProductsInitCm;
    public static double whetherQuitIq;
    public static double whetherQuitIqSlash;
    public static double engineFactoryAndSupplierNewInitG1G2Low;
    public static double engineFactoryAndSupplierNewInitG1G2Upper;
    public static double engineFactoryAndSupplierNewInitRelationshipStrength;

    @Value("${experiments.weightFactor.a3slash}")
    public void setWeightFactorA3slash(double weightFactorA3slash) {
        CalculationEnum.weightFactorA3slash = weightFactorA3slash;
    }
    @Value("${experiments.weightFactor.b3slash}")
    public void setWeightFactorB3slash(double weightFactorB3slash) {
        CalculationEnum.weightFactorB3slash = weightFactorB3slash;
    }
    @Value("${experiments.weightFactor.x3slash}")
    public void setWeightFactorX3slash(double weightFactorX3slash) {
        CalculationEnum.weightFactorX3slash = weightFactorX3slash;
    }
    @Value("${experiments.weightFactor.y3slash}")
    public void setWeightFactorY3slash(double weightFactorY3slash) {
        CalculationEnum.weightFactorY3slash = weightFactorY3slash;
    }
    @Value("${experiments.relationshipStrength.init.low}")
    public void setRelationshipStrengthInitLow(double relationshipStrengthInitLow) {
        CalculationEnum.relationshipStrengthInitLow = relationshipStrengthInitLow;
    }
    @Value("${experiments.relationshipStrength.init.upper}")
    public void setRelationshipStrengthInitUpper(double relationshipStrengthInitUpper) {
        CalculationEnum.relationshipStrengthInitUpper = relationshipStrengthInitUpper;
    }
    @Value("${experiments.relationshipStrength.a2slash}")
    public void setRelationshipStrengthA2Slash(int relationshipStrengthA2Slash) {
        CalculationEnum.relationshipStrengthA2Slash = relationshipStrengthA2Slash;
    }
    @Value("${experiments.relationshipStrength.ni}")
    public void setRelationshipStrengthNi(int relationshipStrengthNi) {
        CalculationEnum.relationshipStrengthNi = relationshipStrengthNi;
    }
    @Value("${experiments.unassignedTask.rq}")
    public void setRelationshipStrengthRq(double relationshipStrengthRq) {
        CalculationEnum.relationshipStrengthRq = relationshipStrengthRq;
    }
    @Value("${experiments.unassignedTask.rc}")
    public void setRelationshipStrengthRc(double relationshipStrengthRc) {
        CalculationEnum.relationshipStrengthRc = relationshipStrengthRc;
    }
    @Value("${experiments.ap.lm1}")
    public void setApLm1(double apLm1) {
        CalculationEnum.apLm1 = apLm1;
    }
    @Value("${experiments.ap.lm2}")
    public void setApLm2(double apLm2) {
        CalculationEnum.apLm2 = apLm2;
    }
    @Value("${experiments.ap.lm3}")
    public void setApLm3(double apLm3) {
        CalculationEnum.apLm3 = apLm3;
    }
    @Value("${experiments.ap.lm4}")
    public void setApLm4(double apLm4) {
        CalculationEnum.apLm4 = apLm4;
    }
    @Value("${experiments.ap.lm5}")
    public void setApLm5(double apLm5) {
        CalculationEnum.apLm5 = apLm5;
    }
    @Value("${experiments.freight}")
    public void setFreight(int freight) {
        CalculationEnum.freight = freight;
    }
    @Value("${experiments.saleProducts.ejd}")
    public void setSaleProductsEjd(int saleProductsEjd) {
        CalculationEnum.saleProductsEjd = saleProductsEjd;
    }
    @Value("${experiments.saleProducts.Nj}")
    public void setSaleProductsNj(int saleProductsNj) {
        CalculationEnum.saleProductsNj = saleProductsNj;
    }
    @Value("${experiments.saleProducts.cp}")
    public void setSaleProductsCp(double saleProductsCp) {
        CalculationEnum.saleProductsCp = saleProductsCp;
    }
    @Value("${experiments.saleProducts.init.cm}")
    public void setSaleProductsInitCm(int saleProductsInitCm) {
        CalculationEnum.saleProductsInitCm = saleProductsInitCm;
    }
    @Value("${experiments.whetherQuit.iq}")
    public void setWhetherQuitIq(double whetherQuitIq) {
        CalculationEnum.whetherQuitIq = whetherQuitIq;
    }
    @Value("${experiments.whetherQuit.iqSlash}")
    public void setWhetherQuitIqSlash(double whetherQuitIqSlash) {
        CalculationEnum.whetherQuitIqSlash = whetherQuitIqSlash;
    }
    @Value("${experiments.engineFactoryAndSupplier.newInit.g1g2.low}")
    public void setEngineFactoryAndSupplierNewInitG1G2Low(double engineFactoryAndSupplierNewInitG1G2Low) {
        CalculationEnum.engineFactoryAndSupplierNewInitG1G2Low = engineFactoryAndSupplierNewInitG1G2Low;
    }
    @Value("${experiments.engineFactoryAndSupplier.newInit.g1g2.upper}")
    public void setEngineFactoryAndSupplierNewInitG1G2Upper(double engineFactoryAndSupplierNewInitG1G2Upper) {
        CalculationEnum.engineFactoryAndSupplierNewInitG1G2Upper = engineFactoryAndSupplierNewInitG1G2Upper;
    }
    @Value("${experiments.engineFactoryAndSupplier.newInit.relationshipStrength}")
    public void setEngineFactoryAndSupplierNewInitRelationshipStrength(double engineFactoryAndSupplierNewInitRelationshipStrength) {
        CalculationEnum.engineFactoryAndSupplierNewInitRelationshipStrength = engineFactoryAndSupplierNewInitRelationshipStrength;
    }
}
