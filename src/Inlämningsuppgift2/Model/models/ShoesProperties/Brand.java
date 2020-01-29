package Inlämningsuppgift2.Model.models.ShoesProperties;

public class Brand {
    String brandName;

    public Brand(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandName() {
        return brandName;
    }

    @Override
    public String toString() {
        return brandName;
    }
}
