package cn.edu.zju.kpaperproject.utils;

import cn.edu.zju.kpaperproject.enums.EngineFactoryEnum;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
@Component
public class InitEngineFactoryUtils {
    /**
     * 生成主机厂初始x y坐标
     *
     * @return 返回生成(0 - 20)内x y坐标
     */
    public static int[] initPosition() {
        // 0-20
        int x = RandomUtils.nextInt(EngineFactoryEnum.engineFactoryLocationLow, EngineFactoryEnum.engineFactoryLocationUpper + 1);
        int y = RandomUtils.nextInt(EngineFactoryEnum.engineFactoryLocationLow, EngineFactoryEnum.engineFactoryLocationUpper + 1);
        return new int[]{x, y};
    }

    /**
     * 生成主机厂初始总资产
     *
     * @return 初始总资产 1000000-2000000
     */
    public static int initTotalAssets() {
        return RandomUtils.nextInt(EngineFactoryEnum.engineFactoryInitTotalAssetsLow, EngineFactoryEnum.engineFactoryInitTotalAssetsUpper + 1);
    }

    /**
     * 主机厂固定成本
     *
     * @return 常数
     */
    public static int initFixedCost() {
        return EngineFactoryEnum.engineFactoryFixedCost;
    }

    /**
     * 主机厂初始信誉度
     *
     * @return 0.25
     */
    public static double initCredit() {
        return EngineFactoryEnum.engineFactoryInitCredit;
    }


    /**
     * 主机厂初始产能
     *
     * @return 500~2000间的一个随机数
     */
    public static int initCapacity() {
        return RandomUtils.nextInt(EngineFactoryEnum.engineFactoryInitCapacityLow, EngineFactoryEnum.engineFactoryInitCapacityUpper + 1);
    }

    /**
     * 主机厂初始价格
     *
     * @return 价格区间
     */
    public static int[] initPrice() {
        int tmp1 = RandomUtils.nextInt(EngineFactoryEnum.engineFactoryInitPriceLow, EngineFactoryEnum.engineFactoryInitPriceUpper + 1);
        int tmp2 = RandomUtils.nextInt(EngineFactoryEnum.engineFactoryInitPriceLow, EngineFactoryEnum.engineFactoryInitPriceUpper + 1);
        return tmp1 < tmp2 ? new int[]{tmp1, tmp2} : new int[]{tmp2, tmp1};
    }

    /**
     * 主机厂初始质量
     *
     * @return  1~10间的随机数
     */
    public static int initQuality() {
        return RandomUtils.nextInt(EngineFactoryEnum.engineFactoryInitQualityLow, EngineFactoryEnum.engineFactoryInitQualityUpper + 1);
    }
}
