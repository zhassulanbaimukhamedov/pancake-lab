package org.pancakelab.model.pancakes.builder;

import java.util.List;
import java.util.UUID;

public interface PancakeRecipe {
//    default String description()
//    {
//        return "Delicious pancake with %s!".formatted(String.join(", ", ingredients()));
//    }

    // I removed the default description() implementation, because this work for simple pancake classes
    // and each class define its own description logic

    // I will make an abstract method, and use Decorator pattern where each component needs
    // to dynamically modify the description by adding "with Hazelnuts", "with dark chocolate" and so on
    // So, each class will defines its own behavior
    String description();

    // Also I added price() method to demostrate the Decorator design pattern in practice
    // and for support dynamic cost calculation
    double price();

    UUID getOrderId();
    void setOrderId(UUID orderId);
    List<String> ingredients();


}
