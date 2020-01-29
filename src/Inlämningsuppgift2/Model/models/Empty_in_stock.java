package Inlämningsuppgift2.Model.models;

public class Empty_in_stock {
    Shoes shoe;
    String date;

    public Empty_in_stock(Shoes shoe, String date) {
        this.shoe = shoe;
        this.date = date;
    }

    public Shoes getShoe() {
        return shoe;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Empty_in_stock{" +
                "shoe=" + shoe +
                ", date='" + date + '\'' +
                '}';
    }
}
