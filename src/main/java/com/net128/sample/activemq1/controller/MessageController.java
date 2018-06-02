package com.net128.sample.activemq1.controller;

import com.net128.sample.activemq1.db.app.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping("/send")
    public void send(@RequestBody Message message) {
        System.out.println("Sending a message.");
        jmsTemplate.convertAndSend("MessageQueue", message);
    }
}
