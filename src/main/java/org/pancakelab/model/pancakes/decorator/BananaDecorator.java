package org.pancakelab.model.pancakes.decorator;

import org.pancakelab.model.pancakes.builder.PancakeIngredient;
import org.pancakelab.model.pancakes.builder.PancakeRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BananaDecorator implements PancakeRecipe {
    private final PancakeRecipe base;

    public BananaDecorator(PancakeRecipe base) {
        this.base = base;
    }

    @Override
    public String description() {
        return base.description() + ", with "+ PancakeIngredient.BANANA.getDisplayName();
    }

    @Override
    public double price() {
        return base.price() + 1.7;
    }

    @Override
    public UUID getOrderId() {
        return base.getOrderId();
    }

    @Override
    public void setOrderId(UUID orderId) {
        base.setOrderId(orderId);
    }

    @Override
    public List<String> ingredients() {
        List<String> list = new ArrayList<>(base.ingredients());
        list.add(PancakeIngredient.BANANA.getDisplayName());
        return list;
    }
}
