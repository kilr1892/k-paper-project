package cn.edu.zju.kpaperproject.mapper;

import cn.edu.zju.kpaperproject.pojo.TbBalance;
import cn.edu.zju.kpaperproject.pojo.TbBalanceExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbBalanceMapper {
    int countByExample(TbBalanceExample example);

    int deleteByExample(TbBalanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbBalance record);

    int insertSelective(TbBalance record);

    List<TbBalance> selectByExample(TbBalanceExample example);

    TbBalance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbBalance record, @Param("example") TbBalanceExample example);

    int updateByExample(@Param("record") TbBalance record, @Param("example") TbBalanceExample example);

    int updateByPrimaryKeySelective(TbBalance record);

    int updateByPrimaryKey(TbBalance record);
}