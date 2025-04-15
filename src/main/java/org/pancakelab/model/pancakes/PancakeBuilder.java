package org.pancakelab.model.pancakes;

import java.util.ArrayList;
import java.util.List;

public class PancakeBuilder {
    private final List<Ingredient> ingredients = new ArrayList<>();

    public PancakeBuilder withDarkChocolate() {
        ingredients.add(Ingredient.DARK_CHOCOLATE);
        return this;
    }

    public PancakeBuilder withMilkChocolate() {
        ingredients.add(Ingredient.MILK_CHOCOLATE);
        return this;
    }

    public PancakeBuilder withWhippedCream() {
        ingredients.add(Ingredient.WHIPPED_CREAM);
        return this;
    }

    public PancakeBuilder withHazelnuts() {
        ingredients.add(Ingredient.HAZELNUTS);
        return this;
    }

    public PancakeBuilder withMustard() {
        ingredients.add(Ingredient.MUSTARD);
        return this;
    }

    public PancakeBuilder withBanana() {
        ingredients.add(Ingredient.BANANA);
        return this;
    }

    public PancakeRecipe build() {
        return new CustomPancake(ingredients);
    }
}
