package com.study.sharding.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.study.sharding.util.DataSourceUtil;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.ComplexShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
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
//        DruidDataSource dataSource1 = new DruidDataSource();
//        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource1.setUrl("jdbc:mysql://localhost:3306/ds0?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8");
//        dataSource1.setUsername("root");
//        dataSource1.setPassword("123456");
        dataSourceMap.put("ds0", DataSourceUtil.createDataSource("ds0"));

        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("t_order", "ds0.t_order_${0..1}");

        //行表达式分片配置（Groovy表达式配置表路由规则）
        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration
                ("order_id", "t_order_${order_id % 2}"));

        //标准分片配置(对SQL语句中的=, IN和BETWEEN AND的分片)
        //PreciseShardingAlgorithm (=, IN)
        //RangeShardingAlgorithm (BETWEEN AND)
//        orderTableRuleConfig.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration
//                ("order_id", new MyPreciseShardingAlgorithm()));

        //复合分片配置
        orderTableRuleConfig.setTableShardingStrategyConfig(new ComplexShardingStrategyConfiguration
                ("order_id,user_id,status,create_time", new MyComplexKeysShardingAlgorithm()));

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
