package org.pancakelab.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.pancakelab.model.Order;
import org.pancakelab.model.pancakes.builder.PancakeBuilder;
import org.pancakelab.model.pancakes.builder.PancakeIngredient;
import org.pancakelab.model.pancakes.builder.PancakeRecipe;
import org.pancakelab.model.pancakes.validation.Address;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PancakeServiceTest {
//
//    private PancakeService pancakeService = new PancakeService();
//    private Order          order          = null;
//
//    @Test
//    @org.junit.jupiter.api.Order(10)
//    public void GivenOrderDoesNotExist_WhenCreatingOrder_ThenOrderCreatedWithCorrectData_Test() {
//        // exercise
//        order = pancakeService.createOrder(10, 20);
//
//        // verify
//        assertNotNull(order);
//        assertEquals(10, order.getBuilding());
//        assertEquals(20, order.getRoom());
//    }
//
//    @Test
//    @org.junit.jupiter.api.Order(20)
//    public void GivenOrderExists_WhenAddingPancakes_ThenCorrectNumberOfPancakesAdded_Test() {
//        order = pancakeService.createOrder(3, 202);
//
//        for (int i = 0; i < 3; i++) {
//            pancakeService.addPancakeToOrder(
//                    new PancakeBuilder().withDarkChocolate().build(),
//                    order);
//            pancakeService.addPancakeToOrder(
//                    new PancakeBuilder().withMilkChocolate().build(),
//                    order);
//            pancakeService.addPancakeToOrder(
//                    new PancakeBuilder().withMilkChocolate().withHazelnuts().build(),
//                    order);
//        }
//
//        List<String> ordersPancakes = pancakeService.viewOrder(order.getId());
//
//        assertEquals(List.of(
//                "Delicious pancake with dark chocolate!",
//                "Delicious pancake with milk chocolate!",
//                "Delicious pancake with milk chocolate, hazelnuts!",
//                "Delicious pancake with dark chocolate!",
//                "Delicious pancake with milk chocolate!",
//                "Delicious pancake with milk chocolate, hazelnuts!",
//                "Delicious pancake with dark chocolate!",
//                "Delicious pancake with milk chocolate!",
//                "Delicious pancake with milk chocolate, hazelnuts!"
//        ), ordersPancakes);
//    }
//
//    @Test
//    @org.junit.jupiter.api.Order(30)
//    public void GivenPancakesExists_WhenRemovingPancakes_ThenCorrectNumberOfPancakesRemoved_Test() {
//        // setup
//        order = pancakeService.createOrder(5, 105);
//
//        for (int i = 0; i < 3; i++) {
//            pancakeService.addPancakeToOrder(
//                    new PancakeBuilder().withDarkChocolate().build(),
//                    order);
//            pancakeService.addPancakeToOrder(
//                    new PancakeBuilder().withMilkChocolate().build(),
//                    order);
//            pancakeService.addPancakeToOrder(
//                    new PancakeBuilder().withMilkChocolate().withHazelnuts().build(),
//                    order);
//        }
//
//        // exercise
//        pancakeService.removePancakes(
//                new PancakeBuilder().withDarkChocolate().build().description(),
//                order.getId(),
//                2);
//
//        pancakeService.removePancakes(
//                new PancakeBuilder().withMilkChocolate().build().description(),
//                order.getId(),
//                3);
//
//        pancakeService.removePancakes(
//                new PancakeBuilder().withMilkChocolate().withHazelnuts().build().description(),
//                order.getId(),
//                1);
//
//        // verify
//        List<String> ordersPancakes = pancakeService.viewOrder(order.getId());
//        assertTrue(ordersPancakes.containsAll(List.of(
//                "Delicious pancake with dark chocolate!",
//                "Delicious pancake with milk chocolate, hazelnuts!",
//                "Delicious pancake with milk chocolate, hazelnuts!"
//        )) && ordersPancakes.size() == 3);
//
//        // tear down
//    }
//
//    @Test
//    @org.junit.jupiter.api.Order(40)
//    public void GivenOrderExists_WhenCompletingOrder_ThenOrderCompleted_Test() {
//        // setup
//        order = pancakeService.createOrder(1, 101);
//
//        // exercise
//        pancakeService.completeOrder(order.getId());
//
//        // verify
//        Set<UUID> completedOrders = pancakeService.listCompletedOrders();
//        assertTrue(completedOrders.contains(order.getId()));
//
//        // tear down
//    }
//
//    @Test
//    @org.junit.jupiter.api.Order(50)
//    public void GivenOrderExists_WhenPreparingOrder_ThenOrderPrepared_Test() {
//        // setup
//        order = pancakeService.createOrder(2, 202);
//        pancakeService.completeOrder(order.getId());
//
//        // exercise
//        pancakeService.prepareOrder(order.getId());
//
//        // verify
//        Set<UUID> completedOrders = pancakeService.listCompletedOrders();
//        assertFalse(completedOrders.contains(order.getId()));
//
//        Set<UUID> preparedOrders = pancakeService.listPreparedOrders();
//        assertTrue(preparedOrders.contains(order.getId()));
//
//        // tear down
//    }
//
//    @Test
//    @org.junit.jupiter.api.Order(60)
//    public void GivenOrderExists_WhenDeliveringOrder_ThenCorrectOrderReturnedAndOrderRemovedFromTheDatabase_Test() {
//        // setup
//        order = pancakeService.createOrder(7, 707);
//
//        for (int i = 0; i < 2; i++) {
//            pancakeService.addPancakeToOrder(
//                    new PancakeBuilder().withMilkChocolate().withWhippedCream().build(),
//                    order);
//        }
//
//        pancakeService.completeOrder(order.getId());
//        pancakeService.prepareOrder(order.getId());
//
//        List<String> pancakesBeforeDelivery = pancakeService.viewOrder(order.getId());
//
//        // exercise
//        Object[] deliveredOrder = pancakeService.deliverOrder(order.getId());
//
//        // verify
//        assertNotNull(deliveredOrder);
//        assertEquals(order.getId(), ((Order) deliveredOrder[0]).getId());
//        assertEquals(pancakesBeforeDelivery, deliveredOrder[1]);
//
//        Set<UUID> completedOrders = pancakeService.listCompletedOrders();
//        Set<UUID> preparedOrders = pancakeService.listPreparedOrders();
//
//        assertFalse(completedOrders.contains(order.getId()));
//        assertFalse(preparedOrders.contains(order.getId()));
//        assertTrue(pancakeService.viewOrder(order.getId()).isEmpty());
//    }
//
//    @Test
//    @org.junit.jupiter.api.Order(70)
//    public void GivenOrderExists_WhenCancellingOrder_ThenOrderAndPancakesRemoved_Test() {
//        // setup
//        order = pancakeService.createOrder(9, 909);
//
//        for (int i = 0; i < 2; i++) {
//            pancakeService.addPancakeToOrder(
//                    new PancakeBuilder().withDarkChocolate().withHazelnuts().build(),
//                    order);
//        }
//
//        pancakeService.completeOrder(order.getId());
//        pancakeService.prepareOrder(order.getId());
//
//        // exercise
//        pancakeService.cancelOrder(order.getId());
//
//        // verify
//        Set<UUID> completedOrders = pancakeService.listCompletedOrders();
//        Set<UUID> preparedOrders = pancakeService.listPreparedOrders();
//        List<String> pancakes = pancakeService.viewOrder(order.getId());
//
//        assertFalse(completedOrders.contains(order.getId()));
//        assertFalse(preparedOrders.contains(order.getId()));
//        assertTrue(pancakes.isEmpty());
//    }



}
