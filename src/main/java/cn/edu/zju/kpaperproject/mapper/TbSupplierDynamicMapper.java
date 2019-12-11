package cn.edu.zju.kpaperproject.mapper;

import cn.edu.zju.kpaperproject.pojo.TbSupplierDynamic;
import cn.edu.zju.kpaperproject.pojo.TbSupplierDynamicExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbSupplierDynamicMapper {
    int countByExample(TbSupplierDynamicExample example);

    int deleteByExample(TbSupplierDynamicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbSupplierDynamic record);

    int insertSelective(TbSupplierDynamic record);

    List<TbSupplierDynamic> selectByExample(TbSupplierDynamicExample example);

    TbSupplierDynamic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbSupplierDynamic record, @Param("example") TbSupplierDynamicExample example);

    int updateByExample(@Param("record") TbSupplierDynamic record, @Param("example") TbSupplierDynamicExample example);

    int updateByPrimaryKeySelective(TbSupplierDynamic record);

    int updateByPrimaryKey(TbSupplierDynamic record);
}