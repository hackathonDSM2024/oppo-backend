package com.example.demo.domain;

import java.util.*;
import org.springframework.data.repository.*;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
    List<Purchase> findByOrderByIdDesc();
}
