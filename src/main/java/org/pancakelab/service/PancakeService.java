package org.pancakelab.service;

import org.pancakelab.model.Order;
import org.pancakelab.model.pancakes.builder.PancakeRecipe;
import org.pancakelab.model.pancakes.validation.Address;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PancakeService {
//    private final List<Order> orders = Collections.synchronizedList(new ArrayList<>());
//    private final Set<UUID> completedOrders = Collections.synchronizedSet(new HashSet<>());
//    private final Set<UUID> preparedOrders = Collections.synchronizedSet(new HashSet<>());
    private final List<PancakeRecipe> pancakes = Collections.synchronizedList(new ArrayList<>());
    private final OrderService orderService;
    public PancakeService(OrderService orderService) {
        this.orderService = orderService;
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

        Order order = orderService.findOrder(orderId);
        OrderLog.logRemovePancakes(order, description, removedCount.get(), pancakes);
    }


    public synchronized Object[] deliverOrder(UUID orderId) {
//        if (!preparedOrders.contains(orderId)) {
//            throw new IllegalStateException("Order is not prepared and cannot be delivered.");
//        }

        Order order = orderService.findOrder(orderId);
        List<String> pancakesToDeliver = viewOrder(orderId);
        OrderLog.logDeliverOrder(order, this.pancakes);

        pancakes.removeIf(pancake -> pancake.getOrderId().equals(orderId));
        //orders.removeIf(o -> o.getId().equals(orderId));
        //preparedOrders.removeIf(u -> u.equals(orderId));

        return new Object[]{order, pancakesToDeliver};
    }

//
//    public void addMilkChocolateHazelnutsPancake(UUID orderId, int count) {
//        for (int i = 0; i < count; ++i) {
//            addPancake(new MilkChocolateHazelnutsPancake(),
//                    orders.stream().filter(o -> o.getId().equals(orderId)).findFirst().get());
//        }
//    }
}
