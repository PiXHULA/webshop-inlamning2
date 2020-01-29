package Inlämningsuppgift2.Model.models.OrderProperties;

public class Customer_order {
    String customer;
    String customer_order_date;

    public Customer_order(String customer, String customer_order_date) {
        this.customer = customer;
        this.customer_order_date = customer_order_date;
    }

    public String getCustomer() {
        return customer;
    }

    public String getCustomer_order_date() {
        return customer_order_date;
    }

    @Override
    public String toString() {
        return "Customer_order{" +
                "customer='" + customer + '\'' +
                ", customer_order_date='" + customer_order_date + '\'' +
                '}';
    }
}
