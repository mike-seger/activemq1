package com.net128.sample.activemq1.jms;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.SslContext;
import org.apache.activemq.broker.TransportConnector;
import org.apache.activemq.openwire.OpenWireFormat;
import org.apache.activemq.store.jdbc.JDBCPersistenceAdapter;
import org.apache.activemq.store.jdbc.LeaseDatabaseLocker;
import org.apache.camel.util.jsse.KeyManagersParameters;
import org.apache.camel.util.jsse.KeyStoreParameters;
import org.apache.camel.util.jsse.SSLContextParameters;
import org.apache.camel.util.jsse.TrustManagersParameters;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.sql.DataSource;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.List;

@Profile("broker")
@Configuration
public class ActiveMqConfiguration {
    @Value("${spring.activemq.broker-url}")
    private String activeMqBrokerUrl;

    @Value("${spring.activemq.stomp-url}")
    private String activeMqStompUrl;

    @Value("${spring.activemq.stomp-ssl-url}")
    private String activeMqStompSslUrl;

    @Value("${spring.activemq.broker-name}")
    private String activeMqBrokerName;

    @Value("${spring.activemq.locker.acquire-sleep-interval}")
    private int lockAcquireSleepInterval;

    @Value("${spring.activemq.jdbc.lock-keep-alive-period}")
    private int jdbcLockKeepAlivePeriod;

    @Bean
    public BrokerService broker(JDBCPersistenceAdapter jdbcPersistenceAdapter/*, SslContext sslContext*/) throws Exception {
        BrokerFactory.setStartDefault(false);
        BrokerService broker = new BrokerService();
        broker.setBrokerName(activeMqBrokerName);
        broker.addConnector(activeMqBrokerUrl);
        //broker.addConnector(activeMqStompUrl);
        //broker.addConnector(activeMqStompSslUrl);
//        List<TransportConnector> transportConnectors=broker.getTransportConnectors();
//        TransportConnector transportConnector=new TransportConnector();
//        transportConnector.setUri(new URL(activeMqStompSslUrl).toURI());
//        transportConnectors.add(transportConnector);
        broker.setPersistenceAdapter(jdbcPersistenceAdapter);
        broker.setPersistent(true);
        //broker.setSslContext(sslContext);
        broker.start();
        return broker;
    }

    @Bean
    public JDBCPersistenceAdapter persistenceAdapter(@Qualifier("activeMqDataSource") DataSource activeMqDataSource) throws IOException {
        JDBCPersistenceAdapter jdbcPersistenceAdapter=new JDBCPersistenceAdapter(activeMqDataSource, new OpenWireFormat());
        LeaseDatabaseLocker locker = new LeaseDatabaseLocker();
        locker.setDataSource(activeMqDataSource);
        locker.setLeaseHolderId(activeMqBrokerName);
        locker.setLockAcquireSleepInterval(lockAcquireSleepInterval);
        jdbcPersistenceAdapter.setLocker(locker);
        jdbcPersistenceAdapter.setLockKeepAlivePeriod(jdbcLockKeepAlivePeriod);
        return jdbcPersistenceAdapter;
    }

//    @Bean
//    public SslContext sslContext() throws GeneralSecurityException, IOException {
//        return getSSLContextParameters("jsse/broker.ks", "test1234").createSSLContext().getSocketFactory();
//    }

    private SSLContextParameters getSSLContextParameters(String path, String password)  {
        KeyStoreParameters ksp = new KeyStoreParameters();
        ksp.setResource(path);
        ksp.setPassword(password);

        KeyManagersParameters kmp = new KeyManagersParameters();
        kmp.setKeyPassword(password);
        kmp.setKeyStore(ksp);

        TrustManagersParameters tmp = new TrustManagersParameters();
        tmp.setKeyStore(ksp);

        SSLContextParameters sslContextParameters = new SSLContextParameters();
        sslContextParameters.setKeyManagers(kmp);
        sslContextParameters.setTrustManagers(tmp);

        return sslContextParameters;
    }
}
