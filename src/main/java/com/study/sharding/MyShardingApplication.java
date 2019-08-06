package com.study.sharding;

import com.study.sharding.config.MyMessageListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@SpringBootApplication
@MapperScan("com.study.sharding.mapper")
public class MyShardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyShardingApplication.class, args);
    }

    /**
     * RedisMessageListenerContainer提供订阅消息的多路分发，这样多个订阅可以共享同一个Redis连接.
     */
//    @Bean
//    RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory) {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.addMessageListener(new MyMessageListener(), new ChannelTopic("tenmao.blog.channel"));
//        return container;
//    }

}
