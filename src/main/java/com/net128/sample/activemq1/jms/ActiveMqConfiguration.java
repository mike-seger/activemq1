package com.net128.sample.activemq1.jms;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.openwire.OpenWireFormat;
import org.apache.activemq.store.jdbc.JDBCPersistenceAdapter;
import org.apache.activemq.store.jdbc.LeaseDatabaseLocker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class ActiveMqConfiguration {
    @Value("${spring.activemq.broker-url}")
    private String activeMqBrokerUrl;

    @Value("${spring.activemq.broker-name}")
    private String activeMqBrokerName;

    @Value("${spring.activemq.locker.acquire-sleep-interval}")
    private int lockAcquireSleepInterval;

    @Value("${spring.activemq.jdbc.lock-keep-alive-period}")
    private int jdbcLockKeepAlivePeriod;

    @Bean
    public BrokerService broker(JDBCPersistenceAdapter jdbcPersistenceAdapter) throws Exception {
        BrokerFactory.setStartDefault(false);
        BrokerService broker = new BrokerService();
        broker.setBrokerName(activeMqBrokerName);
        broker.setPersistenceAdapter(jdbcPersistenceAdapter);
        broker.addConnector(activeMqBrokerUrl);
        broker.start();
        return broker;
    }

    @Bean
    public JDBCPersistenceAdapter persistenceAdapter(@Qualifier("activeMqDataSource") DataSource activeMqDataSource) throws IOException {
        JDBCPersistenceAdapter jdbcPersistenceAdapter=new JDBCPersistenceAdapter(activeMqDataSource, new OpenWireFormat());
        LeaseDatabaseLocker locker = new LeaseDatabaseLocker();
        locker.setDataSource(activeMqDataSource);
        locker.setLeaseHolderId(activeMqBrokerName);
        //This value must be greater than the LockKeepAlivePeriod
        locker.setLockAcquireSleepInterval(lockAcquireSleepInterval);
        jdbcPersistenceAdapter.setLocker(locker);
        jdbcPersistenceAdapter.setLockKeepAlivePeriod(jdbcLockKeepAlivePeriod);
        return jdbcPersistenceAdapter;
    }
}
