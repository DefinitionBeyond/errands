package com.campus.dev.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DruidPoolProperty {

    @Value("${spring.datasource-pool.initialSize}")
    Integer initialSize;

    @Value("${spring.datasource-pool.minIdle}")
    Integer  minIdle;

    @Value("${spring.datasource-pool.maxActive}")
    Integer  maxActive;

    @Value("${spring.datasource-pool.maxWait}")
    Long maxWait;

    @Value("${spring.datasource-pool.timeBetweenEvictionRunsMillis}")
    Long timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource-pool.minEvictableIdleTimeMillis}")
    Long minEvictableIdleTimeMillis;

    @Value("${spring.datasource-pool.validationQuery}")
    String validationQuery;

    @Value("${spring.datasource-pool.testWhileIdle}")
    Boolean testWhileIdle;

    @Value("${spring.datasource-pool.testOnBorrow}")
    Boolean testOnBorrow;

    @Value("${spring.datasource-pool.testOnReturn}")
    Boolean testOnReturn;

    @Value("${spring.datasource-pool.poolPreparedStatements}")
    Boolean poolPreparedStatements;

    @Value("${spring.datasource-pool.maxPoolPreparedStatementPerConnectionSize}")
    Integer maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource-pool.filters}")
    String filters;

    @Value("${spring.datasource-pool.connectionProperties}")
    String connectionProperties;
}
