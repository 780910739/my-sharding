package com.study.sharding.config;

/**
 * @Author pukaiquan
 * @Date 2019-08-06 17:04
 */
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.lang.NonNull;

@Slf4j
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(@NonNull Message message, byte[] pattern) {
        log.info("message received: {}", message);
    }
}