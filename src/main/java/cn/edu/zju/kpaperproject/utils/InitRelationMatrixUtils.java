package cn.edu.zju.kpaperproject.utils;

import cn.edu.zju.kpaperproject.enums.CalculationEnum;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
@Component
public class InitRelationMatrixUtils {
    /**
     * 初始关系强度
     * @return  0~0.2之间的随机值
     */
    public static double initRelationshipStrengthScore() {
        return RandomUtils.nextDouble(CalculationEnum.relationshipStrengthInitLow, CalculationEnum.relationshipStrengthInitUpper);
    }
}
