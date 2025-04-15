package org.pancakelab;

import org.pancakelab.model.Order;
import org.pancakelab.model.pancakes.PancakeBuilder;
import org.pancakelab.model.pancakes.PancakeRecipe;
import org.pancakelab.service.PancakeService;

import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        PancakeService service = new PancakeService();

        Order order = service.createOrder(3, 202);
        UUID orderId = order.getId();

        PancakeRecipe customPancake = new PancakeBuilder()
                .withMilkChocolate()
                .withWhippedCream()
                .withBanana()
                .build();
        service.addPancakeToOrder(customPancake, order);

        System.out.println("Order in progress:");
        List<String> items = service.viewOrder(orderId);
        for (String item : items) {
            System.out.println("- " + item);
        }

        service.completeOrder(orderId);
        System.out.println("\nThe order is completed and sent to the kitchen.");

        service.prepareOrder(orderId);
        System.out.println("The order is ready");

        Object[] result = service.deliverOrder(orderId);
        System.out.println("\nOrder delivered!");
        Order deliveredOrder = (Order) result[0];
        List<String> deliveredPancakes = (List<String>) result[1];

        System.out.println("Building: " + deliveredOrder.getBuilding());
        System.out.println("Room: " + deliveredOrder.getRoom());
        System.out.println("Pancakes:");
        for (String pancake : deliveredPancakes) {
            System.out.println("- " + pancake);
        }


    }
}