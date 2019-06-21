package com.study.sharding.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.ShardingValue;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 复合分片策略(自定义实现) 对SQL语句中的=,IN和BETWEEN AND的分片,支持多分片键
 *
 * @Author pukaiquan
 * @Date 2019-06-18 14:55
 */
@Slf4j
public class MyComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm {


    @Override
    public Collection<String> doSharding(Collection collection, ComplexKeysShardingValue complexKeysShardingValue) {
        log.info("自定义复合分片策略---");
        log.info("collection:" + JSONObject.toJSONString(collection) + ",shardingValues:" + JSONObject.toJSONString(complexKeysShardingValue));
        //分片逻辑...
        Collection<String> tables = collection;
        Collection<Long> orderIdValues=(Collection<Long>)complexKeysShardingValue.getColumnNameAndShardingValuesMap().get("order_id");
        Collection<Long> userIdValues=(Collection<Long>)complexKeysShardingValue.getColumnNameAndShardingValuesMap().get("user_id");
        List<String> shardingSuffix = new ArrayList<>();
        /*根据user_id + order_id 双分片键来进行分表*/
        if(userIdValues!=null && orderIdValues!=null) {
            for (Long userIdVal : userIdValues) {
                for (Long orderIdVal : orderIdValues) {
                    String suffix = userIdVal % 2 + "_" + orderIdVal % 2;
                    tables.forEach(x -> {
                        if (x.endsWith(suffix)) {
                            shardingSuffix.add(x.toString());
                        }
                    });
                }
            }
        }else {
            return tables;
        }
        log.info("分片结果---{}",JSONObject.toJSONString(shardingSuffix));
        return shardingSuffix;
    }


}
