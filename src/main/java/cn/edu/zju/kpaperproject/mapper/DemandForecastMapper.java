package cn.edu.zju.kpaperproject.mapper;

import cn.edu.zju.kpaperproject.pojo.DemandForecast;
import cn.edu.zju.kpaperproject.pojo.DemandForecastExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandForecastMapper {
    int countByExample(DemandForecastExample example);

    int deleteByExample(DemandForecastExample example);

    int deleteByPrimaryKey(Integer cycleTimes);

    int insert(DemandForecast record);

    int insertSelective(DemandForecast record);

    List<DemandForecast> selectByExample(DemandForecastExample example);

    DemandForecast selectByPrimaryKey(Integer cycleTimes);

    int updateByExampleSelective(@Param("record") DemandForecast record, @Param("example") DemandForecastExample example);

    int updateByExample(@Param("record") DemandForecast record, @Param("example") DemandForecastExample example);

    int updateByPrimaryKeySelective(DemandForecast record);

    int updateByPrimaryKey(DemandForecast record);
}