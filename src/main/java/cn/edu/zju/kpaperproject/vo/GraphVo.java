package cn.edu.zju.kpaperproject.vo;

import cn.edu.zju.kpaperproject.pojo.TbEngineFactory;
import cn.edu.zju.kpaperproject.pojo.TbEngineFactoryDynamic;
import cn.edu.zju.kpaperproject.pojo.TbSupplier;
import cn.edu.zju.kpaperproject.pojo.TbSupplierDynamic;
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
    private TbEngineFactory tbEngineFactory;
    private TbSupplier tbSupplier;
    //    private int engineFactoryTotalAssetsP;
//    private int supplierTotalAssetsP;
    private TbEngineFactoryDynamic tbEngineFactoryDynamic;
    private TbSupplierDynamic tbSupplierDynamic;
}
