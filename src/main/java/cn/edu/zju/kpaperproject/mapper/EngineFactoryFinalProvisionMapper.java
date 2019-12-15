package cn.edu.zju.kpaperproject.mapper;

import cn.edu.zju.kpaperproject.pojo.EngineFactoryFinalProvision;
import cn.edu.zju.kpaperproject.pojo.EngineFactoryFinalProvisionExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EngineFactoryFinalProvisionMapper {
    int countByExample(EngineFactoryFinalProvisionExample example);

    int deleteByExample(EngineFactoryFinalProvisionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EngineFactoryFinalProvision record);

    int insertSelective(EngineFactoryFinalProvision record);

    List<EngineFactoryFinalProvision> selectByExample(EngineFactoryFinalProvisionExample example);

    EngineFactoryFinalProvision selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EngineFactoryFinalProvision record, @Param("example") EngineFactoryFinalProvisionExample example);

    int updateByExample(@Param("record") EngineFactoryFinalProvision record, @Param("example") EngineFactoryFinalProvisionExample example);

    int updateByPrimaryKeySelective(EngineFactoryFinalProvision record);

    int updateByPrimaryKey(EngineFactoryFinalProvision record);
// --------------------------
    void insertList(List<EngineFactoryFinalProvision> listEngineFactoryFinalProvision);
}