package cn.edu.zju.kpaperproject.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 主机厂最终能向市场提供的产品.
 *
 * @author RichardLee
 * @version v1.0
 */
@Getter
@Setter
public class EngineFactoryFinalProvision {
    /** 主机厂Id */
    private String engineFactoryId;
    /** 最终产品数量 */
    private int finalProductNumber;
    /** 最终面向市场价格 */
    private int finalMarketPrice;
    /** 最终质量 */
    private int finalMarketQuality;
    /** 市场需求数量 */
    private int marketNeedNumber;

}
