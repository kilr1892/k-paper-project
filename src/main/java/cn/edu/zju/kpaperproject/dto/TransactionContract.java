package cn.edu.zju.kpaperproject.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 精匹配后交易契约
 *
 * @author RichardLee
 * @version v1.0
 */
@Getter
@Setter
public class TransactionContract {
    /** 主机厂id */
    private String engineFactoryId;
    /** 主机厂信誉度 */
    private double engineFactoryCredit;
    /** 主机厂位置坐标 */
    private int[] engineFactoryLocationXY;
    /** 主机厂初始期望的价格 */
    private int[] engineFactory2ServiceOfferPrice;
    /** 供应商id */
    private String supplierId;
    /** 供应商信誉度 */
    private double supplierCredit;
    /** 供应商位置坐标 */
    private int[] supplierLocationXY;
    /** 任务类型 */
    private int taskType;
    /** 主机厂任务需求量 */
    private int engineFactoryNeedServiceNumber;
    /** 成交价格 */
    private int orderPrice;
    /** 成交质量 */
    private int orderQuality;
}
