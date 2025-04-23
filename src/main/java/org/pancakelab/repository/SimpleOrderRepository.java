package org.pancakelab.repository;

import org.pancakelab.model.Order;

import java.util.*;

public class SimpleOrderRepository implements OrderRepository{
    private final Map<UUID, Order> storage = new HashMap<>();
    @Override
    public void save(Order order) {
        storage.put(order.getId(), order);
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void delete(UUID id) {
        storage.remove(id);
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(storage.values());
    }
}
