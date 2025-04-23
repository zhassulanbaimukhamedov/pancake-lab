package org.pancakelab.model.pancakes.validation;

public record Address(int building, int room) {
    public Address {
        if (building <= 0) {
            throw new IllegalArgumentException("Building number must be positive.");

        }
        if (room <= 0) {
            throw new IllegalArgumentException("Room number must be positive.");
        }
    }
}
