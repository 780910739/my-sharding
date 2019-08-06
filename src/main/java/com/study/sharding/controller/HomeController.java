package com.study.sharding.controller;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author pukaiquan
 * @Date 2019-08-06 17:03
 */
@RestController
public class HomeController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping
    public String ping() {
        //往tenmao.blog.channel发送消息"hello world"
        stringRedisTemplate.convertAndSend("tenmao.blog.channel", "hello world");
        return "success";
    }


    @RabbitListener(queues ="GPS_QUERY")
    public void test(String str, Channel channel, Message message) {
         try {
             System.out.println("消费===》 "+str);
             //channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
    }
}