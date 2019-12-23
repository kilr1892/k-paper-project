package cn.edu.zju.kpaperproject.vo;

import cn.edu.zju.kpaperproject.pojo.TbEngineFactory;
import cn.edu.zju.kpaperproject.pojo.TbSupplier;
import lombok.Data;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
@Data
public class GraphVo {
    private String engineFactoryId;
    private String supplierId;
    private double relationScore;
//    private double engineFactoryLocationGX;
//    private double engineFactoryLocationGY;
//    private double supplierLocationGX;
//    private double supplierLocationGY;

    private TbEngineFactory tbEngineFactory;
    private TbSupplier tbSupplier;
}
