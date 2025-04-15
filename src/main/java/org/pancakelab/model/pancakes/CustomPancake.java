package org.pancakelab.model.pancakes;

import java.util.List;
import java.util.UUID;

public class CustomPancake implements PancakeRecipe {
    private final List<Ingredient> ingredients;
    private UUID orderId;

    public CustomPancake(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public UUID getOrderId() {
        return orderId;
    }

    @Override
    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    @Override
    public List<String> ingredients() {
        return ingredients.stream()
                .map(i -> i.name().toLowerCase().replace('_', ' '))
                .toList();
    }
}
