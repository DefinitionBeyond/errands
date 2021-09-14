package com.campus.dev.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Configuration
@MapperScan(basePackages = {"com.campus.dev.dao.mapper"} )
public class DruidConfig {
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Autowired
    private DruidPoolProperty druidPoolProperty;

    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.userName);
        dataSource.setPassword(this.password);
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setInitialSize(this.druidPoolProperty.initialSize);
        dataSource.setMinIdle(this.druidPoolProperty.minIdle);
        dataSource.setMaxActive(this.druidPoolProperty.maxActive);
        dataSource.setMaxWait(this.druidPoolProperty.maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(this.druidPoolProperty.timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(this.druidPoolProperty.minEvictableIdleTimeMillis);
        dataSource.setTestWhileIdle(this.druidPoolProperty.testWhileIdle);
        dataSource.setTestOnBorrow(this.druidPoolProperty.testOnBorrow);
        dataSource.setTestOnReturn(this.druidPoolProperty.testOnReturn);
        dataSource.setPoolPreparedStatements(this.druidPoolProperty.poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(this.druidPoolProperty.maxPoolPreparedStatementPerConnectionSize);
        dataSource.setProxyFilters(getWallFilters());
        try {
            dataSource.setFilters(this.druidPoolProperty.filters);
        } catch (SQLException e) {
//            logger.error("errands-service druid configuration initialization filter", e);
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        dataSource.setConnectionProperties(this.druidPoolProperty.connectionProperties);
        return dataSource;
    }

    /**
     * 多条sql同时执行
     */
    private List<Filter> getWallFilters(){
        WallFilter wallFilter = new WallFilter();
        WallConfig wallConfig =new WallConfig();
        wallConfig.setMultiStatementAllow(true);
        wallFilter.setConfig(wallConfig);

        return Arrays.asList(wallFilter);
    }

}
