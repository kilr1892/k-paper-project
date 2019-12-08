package cn.edu.zju.kpaperproject.mapper;

import cn.edu.zju.kpaperproject.pojo.TbMarket;
import cn.edu.zju.kpaperproject.pojo.TbMarketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbMarketMapper {
    int countByExample(TbMarketExample example);

    int deleteByExample(TbMarketExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbMarket record);

    int insertSelective(TbMarket record);

    List<TbMarket> selectByExample(TbMarketExample example);

    TbMarket selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbMarket record, @Param("example") TbMarketExample example);

    int updateByExample(@Param("record") TbMarket record, @Param("example") TbMarketExample example);

    int updateByPrimaryKeySelective(TbMarket record);

    int updateByPrimaryKey(TbMarket record);
}