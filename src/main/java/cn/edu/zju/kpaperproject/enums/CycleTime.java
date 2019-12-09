package cn.edu.zju.kpaperproject.enums;

import org.springframework.stereotype.Component;

/**
 * 数字相关的枚举类.
 *
 * @author RichardLee
 * @version v1.0
 */
@Component
public class CycleTime {
    public static final int QUALITY_STEP = 1;
    public static final int CYCLE_TIME_INIT = 0;
    public static final int POSITION_X = 0;
    public static final int POSITION_Y = 1;
    public static final int PRICE_LOW = 0;
    public static final int PRICE_UPPER = 1;
    public static final int QUALITY_UPPER = 10;
}