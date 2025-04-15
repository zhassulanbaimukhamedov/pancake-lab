package org.pancakelab.service;

import org.pancakelab.model.Order;
import org.pancakelab.model.pancakes.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PancakeService {
    private final List<Order>         orders          = Collections.synchronizedList(new ArrayList<>());
    private final Set<UUID>           completedOrders = Collections.synchronizedSet(new HashSet<>());
    private final Set<UUID>           preparedOrders  = Collections.synchronizedSet(new HashSet<>());
    private final List<PancakeRecipe> pancakes        = Collections.synchronizedList(new ArrayList<>());

    public Order createOrder(int building, int room) {
        if (building <= 0 || room <= 0) {
            throw new IllegalArgumentException("Building and room must be greater than 0.");
        }
        Order order = new Order(building, room);
        orders.add(order);
        return order;
    }

    private Order findOrder(UUID orderId) {
        return orders.stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
    }

    public List<String> viewOrder(UUID orderId) {
        return pancakes.stream()
                       .filter(pancake -> pancake.getOrderId().equals(orderId))
                       .map(PancakeRecipe::description).toList();
    }

    public void addPancakeToOrder(PancakeRecipe pancake, Order order) {
        pancake.setOrderId(order.getId());
        pancakes.add(pancake);
        OrderLog.logAddPancake(order, pancake.description(), pancakes);
    }


    public synchronized void removePancakes(String description, UUID orderId, int count) {
        final AtomicInteger removedCount = new AtomicInteger(0);
        pancakes.removeIf(pancake -> {
            return pancake.getOrderId().equals(orderId) &&
                   pancake.description().equals(description) &&
                   removedCount.getAndIncrement() < count;
        });

        Order order = findOrder(orderId);
        OrderLog.logRemovePancakes(order, description, removedCount.get(), pancakes);
    }

    public synchronized void cancelOrder(UUID orderId) {
        Order order = findOrder(orderId);
        OrderLog.logCancelOrder(order, this.pancakes);

        pancakes.removeIf(pancake -> pancake.getOrderId().equals(orderId));
        orders.removeIf(o -> o.getId().equals(orderId));
        completedOrders.removeIf(u -> u.equals(orderId));
        preparedOrders.removeIf(u -> u.equals(orderId));

        OrderLog.logCancelOrder(order,pancakes);
    }

    public synchronized void completeOrder(UUID orderId) {
        completedOrders.add(orderId);
    }

    public Set<UUID> listCompletedOrders() {
        return completedOrders;
    }

    public synchronized void prepareOrder(UUID orderId) {
        preparedOrders.add(orderId);
        completedOrders.removeIf(u -> u.equals(orderId));
    }

    public Set<UUID> listPreparedOrders() {
        return preparedOrders;
    }

    public synchronized Object[] deliverOrder(UUID orderId) {
        if (!preparedOrders.contains(orderId)) {
            throw new IllegalStateException("Order is not prepared and cannot be delivered.");
        }

        Order order = findOrder(orderId);
        List<String> pancakesToDeliver = viewOrder(orderId);
        OrderLog.logDeliverOrder(order, this.pancakes);

        pancakes.removeIf(pancake -> pancake.getOrderId().equals(orderId));
        orders.removeIf(o -> o.getId().equals(orderId));
        preparedOrders.removeIf(u -> u.equals(orderId));

        return new Object[] {order, pancakesToDeliver};
    }
}
