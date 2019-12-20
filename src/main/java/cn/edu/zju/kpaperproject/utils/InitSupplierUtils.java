package cn.edu.zju.kpaperproject.utils;

import cn.edu.zju.kpaperproject.enums.SupplierEnum;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
@Component
public class InitSupplierUtils {
    /**
     * 生成供应商的地址
     *
     * @param mapPosition 存在的地址
     * @return x y取值为0~20间的
     */
    public static double[] initPosition(Map<Double, Double> mapPosition) {
        while (true) {
            double x = RandomUtils.nextInt(SupplierEnum.supplierLocationLow, SupplierEnum.supplierLocationUpper);
            double y = RandomUtils.nextInt(SupplierEnum.supplierLocationLow, SupplierEnum.supplierLocationUpper);
            // 强行一位小数
            x = (int) (x * 10) / 10;
            y = (int) (y * 10) / 10;
            // 通过x取得y值
            Double valueY = mapPosition.get(x);
            if (valueY == null) {
                // 没有x的key, 就是ok的
                return new double[]{x, y};
            } else {
                // 有相同的x, 看看y一不一样
                if (y != valueY) {
                    mapPosition.put(x, y);
                    return new double[]{x, y};
                }
            }
        }
    }

    /**
     * 生成供应商初始总资产
     *
     * @return 初始总资产 300000-600000
     */
    public static int initTotalAssets() {
        return RandomUtils.nextInt(SupplierEnum.supplierInitTotalAssetsLow, SupplierEnum.supplierInitTotalAssetsUpper + 1);
    }

    /**
     * 供应商固定成本
     *
     * @return 常数30000
     */
    public static int initFixedCost() {
        return SupplierEnum.supplierFixedCost;
    }

    /**
     * 供应商初始信誉度
     *
     * @return 0.1
     */
    public static double initCredit() {
        return SupplierEnum.supplierInitCredit;
    }

    /**
     * 初始化供应商类型
     *
     * @param type 类型代码
     * @return 类型代码
     */
    public static int initType(int type) {
        switch (type) {
            case 210:
                return SupplierEnum.supplierType210;
            case 220:
                return SupplierEnum.supplierType220;
            case 230:
                return SupplierEnum.supplierType230;
            case 240:
                return SupplierEnum.supplierType240;
            case 250:
                return SupplierEnum.supplierType250;
            default:
                throw new RuntimeException("no such supplier type");
        }
    }

    /**
     * 供应商初始产能
     *
     * @return 600~3000间的一个随机数
     */
    public static int initCapacity() {
        return RandomUtils.nextInt(SupplierEnum.supplierInitCapacityLow, SupplierEnum.supplierInitCapacityUpper + 1);
    }

    /**
     * 供应商初始价格
     *
     * @return 价格区间 100-500内的两个数
     */
    public static int[] initPrice() {
        int tmp1 = RandomUtils.nextInt(SupplierEnum.supplierInitPriceLow, SupplierEnum.supplierInitPriceUpper + 1);
        int tmp2 = RandomUtils.nextInt(SupplierEnum.supplierInitPriceLow, SupplierEnum.supplierInitPriceUpper + 1);
        return tmp1 < tmp2 ? new int[]{tmp1, tmp2} : new int[]{tmp2, tmp1};
    }

    /**
     * 供应商初始质量
     *
     * @return 1~10间的随机数
     */
    public static int initQuality() {
        return RandomUtils.nextInt(SupplierEnum.supplierInitQualityLow, SupplierEnum.supplierInitQualityUpper + 1);
    }
}
