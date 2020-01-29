package Inlämningsuppgift2.Model.models.ShoesProperties;

public class Price {
    String price;

    public Price(String price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return price;
    }

    public String getPrice() {
        return price;
    }
}
