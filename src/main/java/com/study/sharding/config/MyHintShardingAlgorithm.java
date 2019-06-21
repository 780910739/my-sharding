package com.study.sharding.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Hint强制路由
 *
 * @Author pukaiquan
 * @Date 2019-06-21 10:45
 */
@Slf4j
public class MyHintShardingAlgorithm implements HintShardingAlgorithm {

    @Override
    public Collection<String> doSharding(Collection collection, HintShardingValue hintShardingValue) {
        log.info("Hint强制路由---");
        hintShardingValue.getLogicTableName();
        Collection<String> values = (Collection<String>) hintShardingValue.getValues();
        Collection<String> tables = new ArrayList<>();
        Iterator iterator=values.iterator();
        while (iterator.hasNext()) {
            tables.add(hintShardingValue.getLogicTableName() + iterator.next());
        }
        if (tables.size() > 0) {
            return tables;
        }
        return collection;
    }

}
