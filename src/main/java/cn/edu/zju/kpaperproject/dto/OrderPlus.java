//package cn.edu.zju.kpaperproject.dto;
//
//import cn.edu.zju.kpaperproject.pojo.TbOrder;
//import lombok.Getter;
//import lombok.Setter;
//
///**
// * 主机厂与供应商之间的交易最终结果模型
// * (比如是双方否违约/供应商实际质量与价格/ 信誉度等等)
// * TbOrder 补充
// *
// * @author RichardLee
// * @version v1.0
// */
//@Getter
//@Setter
//public class OrderPlus extends TbOrder {
//    // TODO 可能把这个和表格合并, 如果影响性能
//
//    /** 主机厂每个循环开始的信誉度 */
//    private double engineFactoryInitCredit;
//    /** 主机厂新的信誉度 */
//    private double engineFactoryNewCredit;
//    /** 主机厂初始期望价格 */
//    private int[] engineFactoryToServiceOfferPrice;
//    /** 供应商每个循环开始的信誉度 */
//    private double supplierInitCredit;
//    /** 供应商新的信誉度 */
//    private double supplierNewCredit;
//    /** 新的关系强度 */
//    private double relationshipStrength;
//    /** 主机厂利润 */
//    private int engineFactoryProfit;
//    /** 供应商利润 */
//    private int supplierProfit;
//}
