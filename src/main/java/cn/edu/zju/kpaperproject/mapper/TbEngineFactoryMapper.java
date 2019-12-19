package cn.edu.zju.kpaperproject.mapper;

import cn.edu.zju.kpaperproject.pojo.TbEngineFactory;
import cn.edu.zju.kpaperproject.pojo.TbEngineFactoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbEngineFactoryMapper {
    int countByExample(TbEngineFactoryExample example);

    int deleteByExample(TbEngineFactoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbEngineFactory record);

    int insertSelective(TbEngineFactory record);

    List<TbEngineFactory> selectByExample(TbEngineFactoryExample example);

    TbEngineFactory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbEngineFactory record, @Param("example") TbEngineFactoryExample example);

    int updateByExample(@Param("record") TbEngineFactory record, @Param("example") TbEngineFactoryExample example);

    int updateByPrimaryKeySelective(TbEngineFactory record);

    int updateByPrimaryKey(TbEngineFactory record);

    //-------------------------------------------------------------------
    void insertList(List<TbEngineFactory> listEngineFactory);
}