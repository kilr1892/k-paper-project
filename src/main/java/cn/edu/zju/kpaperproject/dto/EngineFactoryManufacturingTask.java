package cn.edu.zju.kpaperproject.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 主机厂制造任务模型
 *
 * @author RichardLee
 * @version v1.0
 */
@Getter @Setter
public class EngineFactoryManufacturingTask {
    /** 任务类型 */
    private int taskType;
    /** 零件需求 */
    private int engineFactoryNeedServiceNumber;
    /** 期望价格区间 */
    private int[] engineFactory2ServiceOfferPrice;
    /** 期望质量 */
    private int engineFactoryExpectedQuality;
}
