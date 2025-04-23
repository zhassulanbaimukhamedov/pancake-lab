package org.pancakelab;

import org.pancakelab.model.Order;
import org.pancakelab.model.pancakes.builder.PancakeBuilder;
import org.pancakelab.model.pancakes.builder.PancakeIngredient;
import org.pancakelab.model.pancakes.builder.PancakeRecipe;
import org.pancakelab.repository.OrderRepository;
import org.pancakelab.repository.SimpleOrderRepository;
import org.pancakelab.service.OrderService;
import org.pancakelab.service.PancakeService;

import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        OrderRepository repo = new SimpleOrderRepository();
        OrderService orderService = new OrderService(repo);
        PancakeService service = new PancakeService(orderService);
        try {
            Order order = orderService.createOrder(3, 202);
            UUID orderId = order.getId();


            PancakeRecipe customPancake = new PancakeBuilder()
                    .add(PancakeIngredient.HAZELNUTS)
                    .build();
            service.addPancakeToOrder(customPancake, order);

            System.out.println("Order in progress:");
            List<String> items = service.viewOrder(orderId);
            for (String item : items) {
                System.out.println("- " + item);
            }

            orderService.completeOrder(orderId);
            System.out.println("\nThe order is completed and sent to the kitchen.");

            orderService.prepareOrder(orderId);
            System.out.println("The order is ready");

            Object[] result = service.deliverOrder(orderId);
            System.out.println("\nOrder delivered!");
            Order deliveredOrder = (Order) result[0];
            List<String> deliveredPancakes = (List<String>) result[1];

            System.out.println("Building: " + deliveredOrder.getAddress().building());
            System.out.println("Room: " + deliveredOrder.getAddress().room());
            System.out.println("Pancakes:");
            for (String pancake : deliveredPancakes) {
                System.out.println("- " + pancake);
            }
        } catch (IllegalArgumentException iEx) {
            System.out.println("Error in argument: " + iEx.getMessage());
        }

    }
}