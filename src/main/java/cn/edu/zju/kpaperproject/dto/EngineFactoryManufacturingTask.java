package cn.edu.zju.kpaperproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 主机厂制造任务分解模型
 *
 * @author RichardLee
 * @version v1.0
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class EngineFactoryManufacturingTask {
    /** 主机厂id */
    private String engineFactoryId;
    /** 主机厂信誉度 */
    private double engineFactoryCredit;
    /** 任务类型 */
    private int taskType;
    /** 零件需求 */
    private int engineFactoryNeedServiceNumber;
    /** 期望价格区间 */
    private int[] engineFactory2ServiceOfferPrice;
    /** 期望质量 */
    private int engineFactoryExpectedQuality;
    /** 位置坐标 */
    private double[] engineFactoryLocationXY;
    /** 匹配度 */
    private double matchDegree;

    public EngineFactoryManufacturingTask(String engineFactoryId, double engineFactoryCredit,double[] engineFactoryLocationXY) {
        this.engineFactoryId = engineFactoryId;
        this.engineFactoryCredit = engineFactoryCredit;
        this.engineFactoryLocationXY = engineFactoryLocationXY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EngineFactoryManufacturingTask that = (EngineFactoryManufacturingTask) o;

        if (taskType != that.taskType) return false;
        return engineFactoryId.equals(that.engineFactoryId);

    }

    @Override
    public int hashCode() {
        int result = engineFactoryId.hashCode();
        result = 31 * result + taskType;
        return result;
    }
}
