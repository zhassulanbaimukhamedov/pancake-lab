package org.pancakelab.repository;

import org.pancakelab.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    void save(Order order);
    Optional<Order> findById(UUID id);
    void delete(UUID id);
    List<Order> findAll();
}
