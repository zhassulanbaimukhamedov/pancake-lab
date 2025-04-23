package org.pancakelab.service;

import org.junit.jupiter.api.Test;
import org.pancakelab.model.Order;
import org.pancakelab.model.pancakes.builder.PancakeBuilder;
import org.pancakelab.model.pancakes.builder.PancakeIngredient;
import org.pancakelab.model.pancakes.builder.PancakeRecipe;
import org.pancakelab.model.pancakes.validation.Address;
import org.pancakelab.repository.OrderRepository;
import org.pancakelab.repository.SimpleOrderRepository;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    OrderRepository repo = new SimpleOrderRepository();
    private OrderService orderService = new OrderService(repo);
    private PancakeService pancakeService = new PancakeService(orderService);
    private Order order = null;

    @Test
    void givenValidAddress_whenCreatingOrder_thenOrderIsCreatedWithAddress() {
        Address address = new Address(1, 101);
        Order order = new Order(address);

        assertEquals(address, order.getAddress());
    }
    @Test
    void whenInvalidOrderBuildOrRoom_thenThrowsException_Test(){
//        assertThrows(IllegalArgumentException.class, () -> {
//           pancakeService.createOrder(-1,0);
//        });

        Exception ex = assertThrows(IllegalArgumentException.class, ()->{
            orderService.createOrder(-1, 10);
        });
        System.out.println("Exception message: "+ ex.getMessage());
        assertEquals("Building number must be positive.", ex.getMessage());
    }
}