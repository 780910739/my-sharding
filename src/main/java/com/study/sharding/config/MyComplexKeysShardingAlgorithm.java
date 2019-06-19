package com.study.sharding.config;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        Collection<String> tables=new ArrayList<>();
        List orderIdList=(List) complexKeysShardingValue.getColumnNameAndShardingValuesMap().get("order_id");
        if(orderIdList.size()>0) {
            Long orderId = (Long) orderIdList.get(0);
            if(orderId % 2==0){
                tables.add(complexKeysShardingValue.getLogicTableName()+"_0");
            }else {
                tables.add(complexKeysShardingValue.getLogicTableName()+"_1");
            }
            return tables;
        }
        return tables;
    }
}
