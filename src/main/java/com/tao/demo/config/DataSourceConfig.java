package com.tao.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author tao
 */
@Configuration
public class DataSourceConfig {

    @Bean(destroyMethod = "close", initMethod = "init")
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://111.230.249.118:3306/test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        //dataSource.setFilters("stat");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager txManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource());
    }

//    @Bean
//    public JdbcTemplate jdbcTemplate() throws SQLException {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        jdbcTemplate.setDataSource(dataSource());
//        return jdbcTemplate;
//    }
}
