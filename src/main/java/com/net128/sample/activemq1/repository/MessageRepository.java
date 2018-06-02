package com.net128.sample.activemq1.repository;

import com.net128.sample.activemq1.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, String> {}
