package com.example.demo.domain;

import org.springframework.data.repository.*;

public interface ChatRepository extends CrudRepository<Chat, Long> {
}
