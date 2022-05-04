package model;

import java.time.LocalDateTime;
import java.util.HashSet;

public class Order {
    private LocalDateTime orderDate = LocalDateTime.now();
    private double totalPrice;
    private HashSet<Items> ietms = new HashSet<>();

    public Order() {}

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public HashSet<Items> getIetms() {
        return ietms;
    }

    public void setIetms(HashSet<Items> ietms) {
        this.ietms = ietms;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderDate=" + orderDate +
                ", totalPrice=" + totalPrice +
                ", ietms=" + ietms +
                '}';
    }
}
