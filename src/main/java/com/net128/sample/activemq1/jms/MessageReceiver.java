package com.net128.sample.activemq1.jms;

import com.net128.sample.activemq1.db.app.model.Message;
import com.net128.sample.activemq1.db.app.repository.MessageRepository;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class MessageReceiver {

    @Inject
    private MessageRepository transactionRepository;

    private int count = 1;

    @JmsListener(destination = "MessageQueue", containerFactory = "myFactory")
    public void receiveMessage(Message transaction) {
        try {
            System.out.println("<" + count + "> Received <" + transaction + ">");
            count++;
            transactionRepository.save(transaction);
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
