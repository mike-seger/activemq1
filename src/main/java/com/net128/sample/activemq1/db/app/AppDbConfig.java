package com.net128.sample.activemq1.db.app;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "appEntityManagerFactory",
    transactionManagerRef = "appTransactionManager",
    basePackages = {"com.net128.sample.activemq1.db.app.repository"}
)
public class AppDbConfig {

    @Value("${application.db.app.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Primary
    @Bean(name = "appDataSource")
    @ConfigurationProperties(prefix = "application.db.app.datasource")
    public DataSource appDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "appEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean appEntityManagerFactory(
        EntityManagerFactoryBuilder builder,
        @Qualifier("appDataSource") DataSource appDataSource
    ) {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", ddlAuto);
        return builder
            .dataSource(appDataSource)
            .packages("com.net128.sample.activemq1.db.app.model")
            .persistenceUnit("app")
            .properties(properties)
            .build();
    }

    @Primary
    @Bean(name = "appTransactionManager")
    public PlatformTransactionManager appTransactionManager(
        @Qualifier("appEntityManagerFactory") EntityManagerFactory
            appEntityManagerFactory
    ) {
        return new JpaTransactionManager(appEntityManagerFactory);
    }
}