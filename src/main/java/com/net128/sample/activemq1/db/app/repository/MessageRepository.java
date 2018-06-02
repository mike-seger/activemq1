package com.net128.sample.activemq1.db.app.repository;

import com.net128.sample.activemq1.db.app.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, String> {}
