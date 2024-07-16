package com.example.demo.domain;

import java.util.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;

public interface ChatMessageRepository extends CrudRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatIdOrderByCreatedAt(Long chatId);
    void deleteByChatId(Long chatId);
}

