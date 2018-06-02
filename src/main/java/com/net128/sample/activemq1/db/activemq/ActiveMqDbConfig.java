package com.net128.sample.activemq1.db.activemq;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ActiveMqDbConfig {
    @Bean(name = "activeMqDataSource")
    @ConfigurationProperties(prefix = "application.db.activemq.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}