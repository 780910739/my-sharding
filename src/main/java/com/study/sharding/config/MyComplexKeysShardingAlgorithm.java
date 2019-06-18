package com.study.sharding.config;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;

/**
 * 复合分片策略(自定义实现) 对SQL语句中的=,IN和BETWEEN AND的分片,支持多分片键
 * @Author pukaiquan
 * @Date 2019-06-18 14:55
 */
public class MyComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm {


    @Override
    public Collection<String> doSharding(Collection collection, ComplexKeysShardingValue complexKeysShardingValue) {
        System.out.println("自定义复合分片策略---");
        //分片逻辑...
        Collection<String> tables=collection;
        return tables;
    }
}
