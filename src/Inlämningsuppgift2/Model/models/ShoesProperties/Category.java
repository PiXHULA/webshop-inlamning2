package Inlämningsuppgift2.Model.models.ShoesProperties;

public class Category {
    String category;

    public Category(String category) {
        this.category = category;
    }
    @Override
    public String toString() {
        return category;
    }

    public String getCategory() {
        return category;
    }
}
