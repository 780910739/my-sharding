package com.study.sharding.controller;

import com.study.sharding.entity.TOrder;
import com.study.sharding.mapper.OrderMapper;
import com.study.sharding.util.Sequence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author pukaiquan
 * @Date 2019-06-17 13:57
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping("/add")
    public String addOrder() {
        for (int i=0;i<20;i++) {
            TOrder order = new TOrder();
            order.setOrderId(Sequence.getSequenceId());
            order.setUserId(Sequence.getSequenceId());
//            order.setUserId(8918797918449667l);
//            order.setUserId(8918801079967746l);
            order.setStatus(1);
            order.setCreateTime(LocalDateTime.now());
            orderMapper.insert(order);
        }
        return "SUCCESS";
    }

    @RequestMapping("get")
    public String getOrder(@RequestBody TOrder order) {
        List<TOrder> list = orderMapper.selectById(order);
        return list.toString();
    }

    @RequestMapping("getDetail")
    public String getOrderDetail(@RequestBody TOrder order) {
        List<Map> list = orderMapper.selectDetailById(order.getOrderId());
        return list.toString();
    }

    public static void main(String[] args) {
        System.out.println(Sequence.getSequenceId() % 2);
        System.out.println(Sequence.getSequenceId() % 2);
    }
}
