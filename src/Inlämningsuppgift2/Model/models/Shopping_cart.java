package Inlämningsuppgift2.Model.models;

import Inlämningsuppgift2.Model.models.OrderProperties.Customer_order;

public class Shopping_cart {
    Shoes shoeId;
    int orderId;
    public Shopping_cart(Shoes shoeId, int orderId) {
        this.shoeId = shoeId;
        this.orderId = orderId;
    }

    public Shoes getShoeId() {
        return shoeId;
    }

    public int getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "Shopping_cart{" +
                "shoeId=" + shoeId +
                ", orderId=" + orderId +
                '}';
    }
}
