package org.pancakelab.service;

import org.junit.jupiter.api.Test;
import org.pancakelab.model.Order;
import org.pancakelab.model.pancakes.builder.PancakeBuilder;
import org.pancakelab.model.pancakes.builder.PancakeIngredient;
import org.pancakelab.model.pancakes.builder.PancakeRecipe;
import org.pancakelab.repository.OrderRepository;
import org.pancakelab.repository.SimpleOrderRepository;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PancakeBuilderTest {
    OrderRepository repo = new SimpleOrderRepository();
    private OrderService orderService = new OrderService(repo);
    private PancakeService pancakeService = new PancakeService(orderService);
    private Order order = null;

    @Test
    public void GivenOrderExists_WhenAddingPancakes_ThenCorrectDefaultPancake_Test() {
        order = orderService.createOrder(2, 1); // 1. создаём заказ

        pancakeService.addPancakeToOrder(         // 2. добавляем блин
                new PancakeBuilder()
                        .add(PancakeIngredient.MILK_CHOCOLATE)
                        .build(),
                order
        );

        List<String> orderPancakes = pancakeService.viewOrder(order.getId()); // 3. смотрим заказ

        assertEquals(List.of("Delicious pancake, with milk chocolate"), orderPancakes); // 4. проверяем
    }


    @Test
    public void givenMultipleIngredients_whenBuildPancake_thenCorrectDescriptopnAndPrice(){
        PancakeRecipe pancake = new PancakeBuilder()
                .add(PancakeIngredient.HAZELNUTS)
                .build();

        //Item pancake = new PancakeBuilder()
        // .addIngredient(new Ingredient(IngredientName.DARK_CHOCOLATE))
        // .build();

        UUID orderId = UUID.randomUUID();
        pancake.setOrderId(orderId);
        assertEquals("Delicious pancake, with hazelnuts", pancake.description());
        assertEquals(orderId, pancake.getOrderId());
    }

    @Test
    public void whenUnsupportedIngredient_thenThrowsException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new PancakeBuilder()
                    .add(PancakeIngredient.MUSTARD)
                    .build();
        });

        assertEquals("Ingredient MUSTARD is not supported.", ex.getMessage());
    }
}
