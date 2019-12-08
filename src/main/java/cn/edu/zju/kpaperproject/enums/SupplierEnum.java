package cn.edu.zju.kpaperproject.enums;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
public class SupplierEnum {

    @Value("${experiments.supplier.init210}")
    public static int supplierInit210;
    @Value("${experiments.supplier.init220}")
    public static int supplierInit220;
    @Value("${experiments.supplier.init230}")
    public static int supplierInit230;
    @Value("${experiments.supplier.init240}")
    public static int supplierInit240;
    @Value("${experiments.supplier.init250}")
    public static int supplierInit250;

    @Value("${experiments.supplier.location.low}")
    public static int supplierLocationLow;

    @Value("${experiments.supplier.location.upper}")
    public static int supplierLocationUpper;

    @Value("${experiments.supplier.initTotalAssets.low}")
    public static int supplierInitTotalAssetsLow;

    @Value("${experiments.supplier.initTotalAssets.upper}")
    public static int supplierInitTotalAssetsUpper;

    @Value("${experiments.supplier.fixedCost}")
    public static int supplierFixedCost;

    @Value("${experiments.supplier.initCredit}")
    public static double supplierInitCredit;

    @Value("${experiments.supplier.type.210}")
    public static int supplierType210;
    @Value("${experiments.supplier.type.220}")
    public static int  supplierType220;
    @Value("${experiments.supplier.type.230}")
    public static int supplierType230;
    @Value("${experiments.supplier.type.240}")
    public static int supplierType240;
    @Value("${experiments.supplier.type.250}")
    public static int supplierType250;
    @Value("${experiments.supplier.initCapacity.low}")
    public static int supplierInitCapacityLow;
    @Value("${experiments.supplier.initCapacity.upper}")
    public static int supplierInitCapacityUpper;
    @Value("${experiments.supplier.initPrice.low}")
    public static int supplierInitPriceLow;
    @Value("${experiments.supplier.initPrice.upper}")
    public static int supplierInitPriceUpper;
    @Value("${experiments.supplier.quality.low}")
    public static int supplierInitQualityLow;
    @Value("${experiments.supplier.quality.upper}")
    public static int supplierInitQualityUpper;

}

