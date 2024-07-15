package com.example.demo.domain;

import java.util.*;
import org.springframework.data.repository.*;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByAccountId(String accountId);
}