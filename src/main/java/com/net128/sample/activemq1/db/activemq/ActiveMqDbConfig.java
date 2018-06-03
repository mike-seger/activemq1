package com.net128.sample.activemq1.db.activemq;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActiveMqDbConfig {
    @ConfigurationProperties("application.db.activemq.datasource")
    public DataSourceProperties activeMqDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "activeMqDataSource")
    @ConfigurationProperties("application.db.activemq.datasource")
    public HikariDataSource activeMqDataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
}