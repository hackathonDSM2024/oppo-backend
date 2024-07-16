package com.example.demo.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;

public interface ChatMessageRepository extends CrudRepository<Purchase, Long> {
}

