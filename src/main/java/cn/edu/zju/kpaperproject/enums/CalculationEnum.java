package cn.edu.zju.kpaperproject.enums;

import org.springframework.beans.factory.annotation.Value;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
public class CalculationEnum {
    @Value("${experiments.weightFactor.a3slash}")
    public static double weightFactorA3slash;
    @Value("${experiments.weightFactor.b3slash}")
    public static double weightFactorB3slash;
    @Value("${experiments.weightFactor.x3slash}")
    public static double weightFactorX3slash;
    @Value("${experiments.weightFactor.y3slash}")
    public static double weightFactorY3slash;

    @Value("${experiments.relationshipStrength.init.low}")
    public static double relationshipStrengthInitLow;
    @Value("${experiments.relationshipStrength.init.upper}")
    public static double relationshipStrengthInitUpper;
    @Value("${experiments.relationshipStrength.a2slash}")
    public static int relationshipStrengthA2Slash;
    @Value("${experiments.relationshipStrength.ni}")
    public static int relationshipStrengthNi;
    @Value("${experiments.unassignedTask.rq}")
    public static double relationshipStrengthRq;
    @Value("${experiments.unassignedTask.rc}")
    public static double relationshipStrengthRc;
    @Value("${experiments.ap.lm1}")
    public static double apLm1;
    @Value("${experiments.ap.lm2}")
    public static double apLm2;
    @Value("${experiments.ap.lm3}")
    public static double apLm3;
    @Value("${experiments.ap.lm4}")
    public static double apLm4;
    @Value("${experiments.ap.lm5}")
    public static double apLm5;
    @Value("${experiments.freight}")
    public static int freight;
    @Value("${experiments.saleProducts.ejd}")
    public static int saleProductsEjd;
    @Value("${experiments.saleProducts.Nj}")
    public static int saleProductsNj;
    @Value("${experiments.saleProducts.cp}")
    public static double saleProductsCp;
    @Value("${experiments.saleProducts.init.cm}")
    public static int saleProductsInitCm;
    @Value("${experiments.whetherQuit.iq}")
    public static double whetherQuitIq;
    @Value("${experiments.whetherQuit.iqSlash}")
    public static double whetherQuitIqSlash;
    @Value("${experiments.engineFactoryAndSupplier.newInit.g1g2.low}")
    public static double engineFactoryAndSupplierNewInitG1G2Low;
    @Value("${experiments.engineFactoryAndSupplier.newInit.g1g2.upper}")
    public static double engineFactoryAndSupplierNewInitG1G2Upper;
    @Value("${experiments.engineFactoryAndSupplier.newInit.relationshipStrength}")
    public static double engineFactoryAndSupplierNewInitRelationshipStrength;
}
