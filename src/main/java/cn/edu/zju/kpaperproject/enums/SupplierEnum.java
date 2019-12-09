package cn.edu.zju.kpaperproject.enums;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 供应商相关枚举类
 *
 * @author RichardLee
 * @version v1.0
 */
@Component
public class SupplierEnum {

    public static int supplierInit210Sum;
    public static int supplierInit220Sum;
    public static int supplierInit230Sum;
    public static int supplierInit240Sum;
    public static int supplierInit250Sum;

    public static int supplierLocationLow;

    public static int supplierLocationUpper;

    public static int supplierInitTotalAssetsLow;

    public static int supplierInitTotalAssetsUpper;

    public static int supplierFixedCost;

    public static double supplierInitCredit;

    public static int supplierType210;
    public static int supplierType220;
    public static int supplierType230;
    public static int supplierType240;
    public static int supplierType250;
    public static int supplierInitCapacityLow;
    public static int supplierInitCapacityUpper;
    public static int supplierInitPriceLow;
    public static int supplierInitPriceUpper;
    public static int supplierInitQualityLow;
    public static int supplierInitQualityUpper;


    public static int[] getSupplierTypeCodes() {
        return new int[]{SupplierEnum.supplierType210, SupplierEnum.supplierType220, SupplierEnum.supplierType230
                , SupplierEnum.supplierType240, SupplierEnum.supplierType250};
    }

    @Value("${experiments.supplier.init210Sum}")
    public void setSupplierInit210Sum(int supplierInit210Sum) {
        SupplierEnum.supplierInit210Sum = supplierInit210Sum;
    }

    @Value("${experiments.supplier.init220Sum}")
    public void setSupplierInit220Sum(int supplierInit220Sum) {
        SupplierEnum.supplierInit220Sum = supplierInit220Sum;
    }

    @Value("${experiments.supplier.init230Sum}")
    public void setSupplierInit230Sum(int supplierInit230Sum) {
        SupplierEnum.supplierInit230Sum = supplierInit230Sum;
    }

    @Value("${experiments.supplier.init240Sum}")
    public void setSupplierInit240Sum(int supplierInit240Sum) {
        SupplierEnum.supplierInit240Sum = supplierInit240Sum;
    }

    @Value("${experiments.supplier.init250Sum}")
    public void setSupplierInit250Sum(int supplierInit250Sum) {
        SupplierEnum.supplierInit250Sum = supplierInit250Sum;
    }

    @Value("${experiments.supplier.location.low}")
    public void setSupplierLocationLow(int supplierLocationLow) {
        SupplierEnum.supplierLocationLow = supplierLocationLow;
    }

    @Value("${experiments.supplier.location.upper}")
    public void setSupplierLocationUpper(int supplierLocationUpper) {
        SupplierEnum.supplierLocationUpper = supplierLocationUpper;
    }

    @Value("${experiments.supplier.initTotalAssets.low}")
    public void setSupplierInitTotalAssetsLow(int supplierInitTotalAssetsLow) {
        SupplierEnum.supplierInitTotalAssetsLow = supplierInitTotalAssetsLow;
    }

    @Value("${experiments.supplier.initTotalAssets.upper}")
    public void setSupplierInitTotalAssetsUpper(int supplierInitTotalAssetsUpper) {
        SupplierEnum.supplierInitTotalAssetsUpper = supplierInitTotalAssetsUpper;
    }

    @Value("${experiments.supplier.fixedCost}")
    public void setSupplierFixedCost(int supplierFixedCost) {
        SupplierEnum.supplierFixedCost = supplierFixedCost;
    }

    @Value("${experiments.supplier.initCredit}")
    public void setSupplierInitCredit(double supplierInitCredit) {
        SupplierEnum.supplierInitCredit = supplierInitCredit;
    }

    @Value("${experiments.supplier.type.210}")
    public void setSupplierType210(int supplierType210) {
        SupplierEnum.supplierType210 = supplierType210;
    }

    @Value("${experiments.supplier.type.220}")
    public void setSupplierType220(int supplierType220) {
        SupplierEnum.supplierType220 = supplierType220;
    }

    @Value("${experiments.supplier.type.230}")
    public void setSupplierType230(int supplierType230) {
        SupplierEnum.supplierType230 = supplierType230;
    }

    @Value("${experiments.supplier.type.240}")
    public void setSupplierType240(int supplierType240) {
        SupplierEnum.supplierType240 = supplierType240;
    }

    @Value("${experiments.supplier.type.250}")
    public void setSupplierType250(int supplierType250) {
        SupplierEnum.supplierType250 = supplierType250;
    }

    @Value("${experiments.supplier.initCapacity.low}")
    public void setSupplierInitCapacityLow(int supplierInitCapacityLow) {
        SupplierEnum.supplierInitCapacityLow = supplierInitCapacityLow;
    }

    @Value("${experiments.supplier.initCapacity.upper}")
    public void setSupplierInitCapacityUpper(int supplierInitCapacityUpper) {
        SupplierEnum.supplierInitCapacityUpper = supplierInitCapacityUpper;
    }

    @Value("${experiments.supplier.initPrice.low}")
    public void setSupplierInitPriceLow(int supplierInitPriceLow) {
        SupplierEnum.supplierInitPriceLow = supplierInitPriceLow;
    }

    @Value("${experiments.supplier.initPrice.upper}")
    public void setSupplierInitPriceUpper(int supplierInitPriceUpper) {
        SupplierEnum.supplierInitPriceUpper = supplierInitPriceUpper;
    }

    @Value("${experiments.supplier.quality.low}")
    public void setSupplierInitQualityLow(int supplierInitQualityLow) {
        SupplierEnum.supplierInitQualityLow = supplierInitQualityLow;
    }

    @Value("${experiments.supplier.quality.upper}")
    public void setSupplierInitQualityUpper(int supplierInitQualityUpper) {
        SupplierEnum.supplierInitQualityUpper = supplierInitQualityUpper;
    }
}

