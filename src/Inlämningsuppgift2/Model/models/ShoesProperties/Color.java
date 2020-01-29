package Inlämningsuppgift2.Model.models.ShoesProperties;

public class Color {
    String color;

    public Color(String color) {
        this.color = color;
    }
    @Override
    public String toString() {
        return color;
    }

    public String getColor() {
        return color;
    }
}
