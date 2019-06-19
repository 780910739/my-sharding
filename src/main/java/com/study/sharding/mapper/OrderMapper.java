package com.study.sharding.mapper;

import com.study.sharding.entity.TOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author pukaiquan
 * @Date 2019-06-17 14:06
 */
public interface OrderMapper {

   void insert(TOrder order);

   List<TOrder> selectById(@Param("order") TOrder order);

   List<Map> selectDetailById(@Param("id") Long id);
}
