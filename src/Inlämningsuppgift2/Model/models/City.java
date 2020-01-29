package Inlämningsuppgift2.Model.models;

public class City {
    String cityName;
    public City(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return cityName;
    }
}