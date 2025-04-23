package org.pancakelab.model.pancakes.builder;

import org.pancakelab.model.pancakes.BasicPancake;
import org.pancakelab.model.pancakes.decorator.*;

import java.util.ArrayList;
import java.util.List;

public class PancakeBuilder {
    private final List<PancakeIngredient> ingredients = new ArrayList<>();

//
//    public PancakeBuilder withDarkChocolate() {
//        ingredients.add(PancakeIngredient.DARK_CHOCOLATE);
//        return this;
//    }
//
//    public PancakeBuilder withMilkChocolate() {
//        ingredients.add(PancakeIngredient.MILK_CHOCOLATE);
//        return this;
//    }
//
//    public PancakeBuilder withWhippedCream() {
//        ingredients.add(PancakeIngredient.WHIPPED_CREAM);
//        return this;
//    }
//
//    public PancakeBuilder withHazelnuts() {
//        ingredients.add(PancakeIngredient.HAZELNUTS);
//        return this;
//    }
//
//    public PancakeBuilder withMustard() {
//        ingredients.add(PancakeIngredient.MUSTARD);
//        return this;
//    }
//
//    public PancakeBuilder withBanana() {
//        ingredients.add(PancakeIngredient.BANANA);
//        return this;
//    }

    public PancakeBuilder add(PancakeIngredient ingredient) {
        ingredients.add(ingredient);
        return this;
    }

    public PancakeRecipe build() {

        PancakeRecipe pancake = new BasicPancake();
        for (PancakeIngredient ingredient : ingredients) {
            switch (ingredient) {
                case HAZELNUTS -> pancake = new HazelnutsDecorator(pancake);
                case BANANA -> pancake = new BananaDecorator(pancake);
                case DARK_CHOCOLATE -> pancake = new DarkChocolateDecorator(pancake);
                case MILK_CHOCOLATE -> pancake = new MilkChocolateDecorator(pancake);
                case WHIPPED_CREAM -> pancake = new WhippedCreamDecorator(pancake);
                default -> throw new IllegalArgumentException("Ingredient " + ingredient + " is not supported.");
            }
        }
        return pancake;
    }
}
