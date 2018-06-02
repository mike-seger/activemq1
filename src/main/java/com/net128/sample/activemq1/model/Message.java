package com.net128.sample.activemq1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString(exclude = "id")
@NoArgsConstructor
public class Message {
    @Id
    private String id;
    private String sender;
    private String recipient;
    private String body;

    @PrePersist
    private void init() {
        id = UUID.randomUUID().toString().replace("-", "");
    }

    public Message(final String sender, final String recipient, final String body) {
        this.sender = sender;
        this.recipient = recipient;
        this.body = body;
    }
}
