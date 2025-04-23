package org.pancakelab.model.pancakes.builder;

public enum PancakeIngredient {
    DARK_CHOCOLATE("dark chocolate"),
    MILK_CHOCOLATE("milk chocolate"),
    WHIPPED_CREAM("whipped cream"),
    HAZELNUTS("hazelnuts"),
    MUSTARD("mustard"),
    BANANA("banana");
    private final String displayName;
    PancakeIngredient(String dispayName){
        this.displayName = dispayName;
    }
    public String getDisplayName(){
        return this.displayName;
    }
}
