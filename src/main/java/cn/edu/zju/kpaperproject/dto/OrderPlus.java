package cn.edu.zju.kpaperproject.dto;

import cn.edu.zju.kpaperproject.pojo.TbOrder;
import lombok.Getter;
import lombok.Setter;

/**
 * TbOrder 补充
 *
 * @author RichardLee
 * @version v1.0
 */
@Getter
@Setter
public class OrderPlus extends TbOrder {
    /** 主机厂初始的信誉度 */
    private double initEngineFactoryCredit;
    /** 主机厂新的信誉度 */
    private double newEngineFactoryCredit;
    /** 供应商初始的信誉度 */
    private double initSupplierCredit;
    /** 供应商新的信誉度 */
    private double newSupplierCredit;
    /** 新的关系强度 */
    private double relationshipStrength;
    // 主机厂利润
    private int engineFactoryProfit;
    // 供应商利润
    private int supplierProfit;
}
