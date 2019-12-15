package cn.edu.zju.kpaperproject.mapper;

import cn.edu.zju.kpaperproject.pojo.OrderPlus;
import cn.edu.zju.kpaperproject.pojo.OrderPlusExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderPlusMapper {
    int countByExample(OrderPlusExample example);

    int deleteByExample(OrderPlusExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderPlus record);

    int insertSelective(OrderPlus record);

    List<OrderPlus> selectByExample(OrderPlusExample example);

    OrderPlus selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderPlus record, @Param("example") OrderPlusExample example);

    int updateByExample(@Param("record") OrderPlus record, @Param("example") OrderPlusExample example);

    int updateByPrimaryKeySelective(OrderPlus record);

    int updateByPrimaryKey(OrderPlus record);

    //-------------------------
    void insertList(List<OrderPlus> listOrderPlus);
}