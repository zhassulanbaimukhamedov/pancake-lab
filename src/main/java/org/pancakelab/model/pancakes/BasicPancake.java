package org.pancakelab.model.pancakes;

import org.pancakelab.model.pancakes.builder.PancakeRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BasicPancake implements PancakeRecipe {
    private UUID orderId;
    @Override
    public String description() {
        return "Delicious pancake";
    }

    @Override
    public double price() {
        return 5.0;
    }

    @Override
    public UUID getOrderId() {
        return this.orderId;
    }

    @Override
    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    @Override
    public List<String> ingredients(){
        return new ArrayList<>();
    }
}
