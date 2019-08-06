package com.study.sharding.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gaoyunfeng
 * @date 20190314
 */
@Configuration
public class AutoTaskRabbitConfig {

    @Bean("GPS_QUERY")
    public Queue saleTaskQueue() {
        return QueueBuilder.durable("GPS_QUERY").build();
    }

}
