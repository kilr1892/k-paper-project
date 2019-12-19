package cn.edu.zju.kpaperproject.mapper;

import cn.edu.zju.kpaperproject.pojo.TbSupplier;
import cn.edu.zju.kpaperproject.pojo.TbSupplierExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbSupplierMapper {
    int countByExample(TbSupplierExample example);

    int deleteByExample(TbSupplierExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbSupplier record);

    int insertSelective(TbSupplier record);

    List<TbSupplier> selectByExample(TbSupplierExample example);

    TbSupplier selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbSupplier record, @Param("example") TbSupplierExample example);

    int updateByExample(@Param("record") TbSupplier record, @Param("example") TbSupplierExample example);

    int updateByPrimaryKeySelective(TbSupplier record);

    int updateByPrimaryKey(TbSupplier record);

    // ----------------------------------------------
    void insertList(List<TbSupplier> listSupplier);
}