package com.study.sharding.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 标准分片策略 对SQL语句中的=, IN和BETWEEN AND的分片操作
 * @Author pukaiquan
 * @Date 2019-06-18 15:30
 */
public class MyPreciseShardingAlgorithm implements PreciseShardingAlgorithm {

    @Override
    public String doSharding(Collection collection, PreciseShardingValue preciseShardingValue) {
        System.out.println("标准分片策略---");
        //分片逻辑...
        return "t_order_1";
    }
}
