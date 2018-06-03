package com.net128.sample.activemq1.db.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Message {
    @Id
    @JsonProperty(access = READ_ONLY)
    @Column(length = 32)
    @Size(max = 32)
    private String id;
    @NotNull
    @Column(length = 256, nullable = false)
    private String sender;
    @NotNull
    @Column(length = 256, nullable = false)
    private String recipient;
    @NotNull
    @Column(length = 4000, nullable = false)
    @Size(max = 4000)
    private String body;
    @JsonProperty(access = READ_ONLY)
    @NotNull
    @Column(nullable = false)
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
