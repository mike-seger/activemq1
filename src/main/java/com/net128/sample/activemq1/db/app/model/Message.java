package com.net128.sample.activemq1.db.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString//(exclude = "id")
@NoArgsConstructor
public class Message {
    @Id
    private String id;
    private String sender;
    private String recipient;
    private String body;
    private LocalDateTime sent;

    @PrePersist
    private void init() {
        id = UUID.randomUUID().toString().replace("-", "");
        sent = LocalDateTime.now();
    }

    public Message(final String sender, final String recipient, final String body) {
        this.sender = sender;
        this.recipient = recipient;
        this.body = body;
    }
}
