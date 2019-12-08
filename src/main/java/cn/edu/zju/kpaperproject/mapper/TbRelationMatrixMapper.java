package cn.edu.zju.kpaperproject.mapper;

import cn.edu.zju.kpaperproject.pojo.TbRelationMatrix;
import cn.edu.zju.kpaperproject.pojo.TbRelationMatrixExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbRelationMatrixMapper {
    int countByExample(TbRelationMatrixExample example);

    int deleteByExample(TbRelationMatrixExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbRelationMatrix record);

    int insertSelective(TbRelationMatrix record);

    List<TbRelationMatrix> selectByExample(TbRelationMatrixExample example);

    TbRelationMatrix selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbRelationMatrix record, @Param("example") TbRelationMatrixExample example);

    int updateByExample(@Param("record") TbRelationMatrix record, @Param("example") TbRelationMatrixExample example);

    int updateByPrimaryKeySelective(TbRelationMatrix record);

    int updateByPrimaryKey(TbRelationMatrix record);
}