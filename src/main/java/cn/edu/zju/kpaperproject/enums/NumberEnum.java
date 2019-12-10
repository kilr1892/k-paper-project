package cn.edu.zju.kpaperproject.enums;

import org.springframework.stereotype.Component;

/**
 * 数字相关的枚举类.
 *
 * @author RichardLee
 * @version v1.0
 */
@Component
public class NumberEnum {
    public static final int QUALITY_STEP = 1;
    public static final int CYCLE_TIME_INIT = 0;
    public static final int POSITION_X_ARRAY_INDEX = 0;
    public static final int POSITION_Y_ARRAY_INDEX = 1;
    public static final int PRICE_LOW_ARRAY_INDEX = 0;
    public static final int PRICE_UPPER_ARRAY_INDEX = 1;
    public static final int QUALITY_LOW_LIMIT = 1;
    public static final int QUALITY_UPPER_LIMIT = 10;
}
