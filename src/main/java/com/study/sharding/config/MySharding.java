package com.study.sharding.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.study.sharding.util.DataSourceUtil;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.*;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.core.rule.ShardingRule;
import org.apache.shardingsphere.core.strategy.route.hint.HintShardingStrategy;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author pukaiquan
 * @Date 2019-06-15 18:00
 */
@Configuration
public class MySharding {

    @Bean(value = "shardingDataSource")
    @Primary
    public DataSource getShardingDataSource() {
        Map<String, DataSource> dataSourceMap = new HashMap<>(1);
        dataSourceMap.put("ds0", DataSourceUtil.createDataSource("ds0"));

        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("t_order", "ds0.t_order_${0..1}_${0..1}");

        //行表达式分片配置（Groovy表达式配置表路由规则）
//        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration
//                ("order_id", "t_order_${order_id % 2}"));



        //复合分片
        orderTableRuleConfig.setTableShardingStrategyConfig(new ComplexShardingStrategyConfiguration
                ("order_id,user_id,status", new MyComplexKeysShardingAlgorithm()));
        //强制路由
        orderTableRuleConfig.setTableShardingStrategyConfig(new HintShardingStrategyConfiguration(
                new MyHintShardingAlgorithm()));
        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);

        // 获取数据源对象
        DataSource dataSource = null;
        Properties properties = new Properties();
        properties.setProperty("sql.show", "true");
        try {
            dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }


}
