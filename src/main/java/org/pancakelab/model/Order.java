package org.pancakelab.model;

import org.pancakelab.model.pancakes.validation.Address;

import java.util.Objects;
import java.util.UUID;

public class Order {
    private final UUID id;

//    private final int building;
//    private final int room;

    private final Address address;

    private OrderStatus status;

    //public Order(int building, int room) {
    public Order(Address adress){
        this.id = UUID.randomUUID();
//        this.building = building;
//        this.room = room;
        this.address = adress;
        this.status = OrderStatus.CREATED;
    }

    public UUID getId() {
        return id;
    }

//    public int getBuilding() {
//        return building;
//    }
//
//    public int getRoom() {
//        return room;
//    }

    public Address getAddress(){
        return this.address;
    }
    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
