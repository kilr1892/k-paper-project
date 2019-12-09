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
@NoArgsConstructor
@Getter
@Setter
public class SupplierTask {
    /** 供应商id */
    private String supplierId;
    /** 任务id */
    private int supplierType;
    /** 服务质量 */
    private int supplierQuality;
    /** 价格区间 */
    private int[] supplierPriceRange;
    /** 服务产能 */
    private int supplierCapacity;
    /** 位置坐标 */
}