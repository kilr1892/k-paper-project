package cn.edu.zju.kpaperproject.mapper;

import cn.edu.zju.kpaperproject.pojo.TbSupplierTypeAvg;
import cn.edu.zju.kpaperproject.pojo.TbSupplierTypeAvgExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface TbSupplierTypeAvgMapper {
    int countByExample(TbSupplierTypeAvgExample example);

    int deleteByExample(TbSupplierTypeAvgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbSupplierTypeAvg record);

    int insertSelective(TbSupplierTypeAvg record);

    List<TbSupplierTypeAvg> selectByExample(TbSupplierTypeAvgExample example);

    TbSupplierTypeAvg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbSupplierTypeAvg record, @Param("example") TbSupplierTypeAvgExample example);

    int updateByExample(@Param("record") TbSupplierTypeAvg record, @Param("example") TbSupplierTypeAvgExample example);

    int updateByPrimaryKeySelective(TbSupplierTypeAvg record);

    int updateByPrimaryKey(TbSupplierTypeAvg record);

    //-------------------------
    void insertList(ArrayList<TbSupplierTypeAvg> listSupplierTypeAvgArray);
}