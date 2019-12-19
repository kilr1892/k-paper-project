package cn.edu.zju.kpaperproject.mapper;

import cn.edu.zju.kpaperproject.pojo.TbTransactionContract;
import cn.edu.zju.kpaperproject.pojo.TbTransactionContractExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbTransactionContractMapper {
    int countByExample(TbTransactionContractExample example);

    int deleteByExample(TbTransactionContractExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbTransactionContract record);

    int insertSelective(TbTransactionContract record);

    List<TbTransactionContract> selectByExample(TbTransactionContractExample example);

    TbTransactionContract selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbTransactionContract record, @Param("example") TbTransactionContractExample example);

    int updateByExample(@Param("record") TbTransactionContract record, @Param("example") TbTransactionContractExample example);

    int updateByPrimaryKeySelective(TbTransactionContract record);

    int updateByPrimaryKey(TbTransactionContract record);

    // ---------
    void insertList(List<TbTransactionContract> listTransactionContractList);
}