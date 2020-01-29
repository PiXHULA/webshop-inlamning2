package Inlämningsuppgift2.Model.models;

public class Customer {
    String firstName;
    String lastName;
    String password;
    City city;

    public Customer(String firstName, String lastName, String password, City city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.city = city;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public City getCity() {
        return city;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " +password + " " + city;
    }
}
