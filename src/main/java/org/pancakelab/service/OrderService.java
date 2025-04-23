package org.pancakelab.service;

import org.pancakelab.model.Order;
import org.pancakelab.model.OrderStatus;
import org.pancakelab.model.pancakes.builder.PancakeRecipe;
import org.pancakelab.model.pancakes.validation.Address;
import org.pancakelab.repository.OrderRepository;

import java.util.*;

public class OrderService {
    private final OrderRepository orderRepository;
    private final List<Order> orders = Collections.synchronizedList(new ArrayList<>());
    private final Set<UUID> completedOrders = Collections.synchronizedSet(new HashSet<>());
    private final Set<UUID> preparedOrders = Collections.synchronizedSet(new HashSet<>());
    private final List<PancakeRecipe> pancakes = Collections.synchronizedList(new ArrayList<>());

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(int building, int room) {
//        if (building <= 0 || room <= 0) {
//            throw new IllegalArgumentException("Building and room must be greater than 0.");
//        }

        //Order order = new Order(building, room);
        Order order = new Order(new Address(building, room));
        //orders.add(order);
        orderRepository.save(order);
        return order;
    }

    public Order findOrder(UUID orderId) {

//        return orders.stream()
//                .filter(o -> o.getId().equals(orderId))
//                .findFirst()
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
    }

    public synchronized void cancelOrder(UUID orderId) {


        Order order = findOrder(orderId);

//        pancakes.removeIf(pancake -> pancake.getOrderId().equals(orderId));
//        orders.removeIf(o -> o.getId().equals(orderId));
//        completedOrders.removeIf(u -> u.equals(orderId));
//        preparedOrders.removeIf(u -> u.equals(orderId));

//        completedOrders.remove(orderId);
//        preparedOrders.remove(orderId);

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.delete(orderId);

        OrderLog.logCancelOrder(order, pancakes);


    }

    public synchronized void completeOrder(UUID orderId) {
        Order order = findOrder(orderId);
        order.setStatus(OrderStatus.COMPLETED);
        //completedOrders.add(orderId);
    }

    public Set<UUID> listCompletedOrders() {
        return completedOrders;
    }

    public synchronized void prepareOrder(UUID orderId) {
//        if (!completedOrders.contains(orderId)) {
//            throw new IllegalStateException("Order must be completed before being prepared.");
//        }
//        preparedOrders.add(orderId);
//        completedOrders.removeIf(u -> u.equals(orderId));

        Order order = findOrder(orderId);
        if (order.getStatus() != OrderStatus.COMPLETED) {
            throw new IllegalStateException("Order must be completed before being prepared.");
        }
        order.setStatus(OrderStatus.PREPARED);
    }

    public Set<UUID> listPreparedOrders() {
        return preparedOrders;
    }

    public void markDelivered(UUID orderId) {
//        if (!preparedOrders.contains(orderId)) {
//            throw new IllegalStateException("Order is not prepared and cannot be delivered.");
//        }
//        preparedOrders.remove(orderId);
//        orderRepository.delete(orderId);

        Order order = findOrder(orderId);
        if (order.getStatus() != OrderStatus.PREPARED) {
            throw new IllegalStateException("Order must be prepared before delivery.");
        }
        order.setStatus(OrderStatus.DELIVERED);
        orderRepository.delete(orderId);
    }

}
