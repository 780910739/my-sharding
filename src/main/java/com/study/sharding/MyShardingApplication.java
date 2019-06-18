package com.study.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.study.sharding.mapper")
public class MyShardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyShardingApplication.class, args);
    }



}
