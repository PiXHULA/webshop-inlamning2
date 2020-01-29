package Inlämningsuppgift2.Model.models;

import Inlämningsuppgift2.Model.models.ShoesProperties.*;

public class Shoes {
    Brand model;
    Size size;
    Color color;
    Price price;
    //List<Category> categoryList;
    int quantity;

    public Shoes(Brand model, Size size, Color color, Price price, int quantity){//, List<Category> categoryList, int quantity) {
        this.model = model;
        this.size = size;
        this.color = color;
        this.price = price;
        //this.categoryList = categoryList;
        this.quantity = quantity;
    }
    public Shoes(Brand model){
        this.model = model;
    }
    public Shoes(){}

    @Override
    public String toString() {
        return "Modell: " + model + ", Färg: " + color + ", Storlek: " + size + ", Pris: " + price;
    }

    public Brand getModel() {
        return model;
    }

    public Size getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public Price getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
