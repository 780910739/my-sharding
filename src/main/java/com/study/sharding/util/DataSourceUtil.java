package com.study.sharding.util;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceUtil {
    
    private static final String HOST = "localhost";
    
    private static final int PORT = 3306;
    
    private static final String USER_NAME = "root";
    
    private static final String PASSWORD = "123456";
    
    public static DataSource createDataSource(final String dataSourceName) {
        HikariDataSource result = new HikariDataSource();
        result.setDriverClassName("com.mysql.jdbc.Driver");
        result.setJdbcUrl("jdbc:mysql://localhost:3306/"+dataSourceName+"?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8");
        result.setUsername(USER_NAME);
        result.setPassword(PASSWORD);
        return result;
    }
}
