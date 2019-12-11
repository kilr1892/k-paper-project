package cn.edu.zju.kpaperproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class SupplierTask {
    /** 供应商id */
    private String supplierId;
    /** 任务id */
    private int supplierType;
    /** 供应商信誉度 */
    private double supplierCredit;
    /** 服务质量 */
    private int supplierQuality;
    /** 价格区间 */
    private int[] supplierPriceRange;
    /** 服务产能 */
    private int supplierCapacity;
    /** 位置坐标 */
    private int[] supplierLocationXY;
    /** 剩余产能 */
    private int supplierRestCapacity;

}
