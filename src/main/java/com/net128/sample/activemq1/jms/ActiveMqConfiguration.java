package com.net128.sample.activemq1.jms;

import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActiveMqConfiguration {
    @Value("${spring.activemq.broker-url}")
    private String activeMqBrokerUrl;

    @Bean
    public BrokerService broker() throws Exception {
        BrokerService broker = new BrokerService();
        broker.addConnector(activeMqBrokerUrl);
        return broker;
    }
}
