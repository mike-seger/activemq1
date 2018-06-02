package com.net128.sample.activemq1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class ActiveMq1Application {
    public static void main(String[] args) {
        SpringApplication.run(ActiveMq1Application.class, args);
    }
}
