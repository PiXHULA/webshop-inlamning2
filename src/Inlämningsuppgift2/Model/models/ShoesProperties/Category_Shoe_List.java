package Inlämningsuppgift2.Model.models.ShoesProperties;

import Inlämningsuppgift2.Model.models.Shoes;

public class Category_Shoe_List {
    Shoes shoe;
    Category category;

    public Category_Shoe_List(Category category, Shoes shoe) {
        this.category = category;
        this.shoe = shoe;
    }

    @Override
    public String toString() {
        return shoe + ", Kategori: " + category;
    }

    public Category getCategory() {
        return category;
    }

    public Shoes getShoe() {
        return shoe;
    }
}
