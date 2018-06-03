package com.net128.sample.activemq1.db.app;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
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
    basePackageClasses = { AppDbConfig.class }
)
public class AppDbConfig {

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${application.db.app.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Primary
    @Bean
    @ConfigurationProperties("application.db.app.datasource")
    public DataSourceProperties appDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "appDataSource")
    @ConfigurationProperties("application.db.app.datasource")
    public HikariDataSource appDataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "appEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean appEntityManagerFactory(
        EntityManagerFactoryBuilder builder,
        @Qualifier("appDataSource") DataSource appDataSource
    ) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", ddlAuto);
        properties.put("hibernate.dialect", hibernateDialect);
        return builder
            .dataSource(appDataSource)
            .packages(getClass().getPackage().getName())
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