package Inlämningsuppgift2.Model;

import Inlämningsuppgift2.Model.models.Customer;
import Inlämningsuppgift2.Model.models.OrderProperties.Customer_order;
import Inlämningsuppgift2.Model.models.Shoes;
import Inlämningsuppgift2.Model.models.ShoesProperties.Category;
import Inlämningsuppgift2.Model.models.ShoesProperties.Category_Shoe_List;
import Inlämningsuppgift2.Model.models.Shopping_cart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Model {
    Repository repository = new Repository();
    public String getProductIdByModelString(String model, String size) {
        Map<Integer, Shoes> fullShoeList = repository.mapShoesToHashmap();
        model.trim().toLowerCase();
        size.trim().toLowerCase();

        String result = fullShoeList.entrySet().stream()
                .filter(x -> model.equals(x.getValue().getModel().getBrandName().trim().toLowerCase()))
                .filter(x -> size.equals(x.getValue().getSize().toString().trim().toLowerCase()))
                .map(x -> x.getKey().toString())
                .collect(Collectors.joining());
        return result;
    }
    public String getCustomer_IdByCustomer(String firstName, String lastName) {
        Map<Integer, Customer> fullCustomerList = repository.mapCustomersToHashmap();
        firstName.trim().isEmpty();
        lastName.trim().isEmpty();
        String result = fullCustomerList.entrySet().stream()
                .filter(x -> firstName.equals(x.getValue().getFirstName().trim().toLowerCase()))
                .filter(x -> lastName.equals(x.getValue().getLastName().trim().toLowerCase()))
                .map(x->x.getKey().toString())
                .collect(Collectors.joining());
        return result;
    }
    public void addToCart(String produkt, String order, String customer) throws SQLException {
        repository.callProcedureAddToCart(produkt,order,customer);
    }
    public boolean isCustomerVerified(String customerName, String customerPassword) {
        Map<Integer, Customer> fullCustomerList = repository.mapCustomersToHashmap();
        customerName.trim().toLowerCase();
        customerPassword.trim().toLowerCase();
        String[]customerInput = customerName.split(" ");
        return fullCustomerList.entrySet().stream()
                .filter(x -> customerInput[0].equals(x.getValue().getFirstName().trim().toLowerCase()))
                .filter(x -> customerInput[1].equals(x.getValue().getLastName().trim().toLowerCase()))
                .anyMatch(e->customerPassword.equals(e.getValue().getPassword().trim().toLowerCase()));
    }
    public Shoes getProductById(int id) {
        Map<Integer, Shoes> shoeList = repository.mapShoesToHashmap();
        return shoeList.get(id);
    }
    public List<String> getAllOrdersByCustomerName(String customerName) {
        customerName.trim().toLowerCase();
        String[] customerInput = customerName.split(" ");
        Map<Integer, Customer> customerList = repository.mapCustomersToHashmap();
        Map<Integer, Customer_order> orderMap = repository.mapOrdersToHashmap();
        Map<Integer, Shopping_cart> shopping_cartMap = repository.mapShopping_cartToHashmap();
        Map<Integer, Shoes> shoes_Map = repository.mapShoesToHashmap();
        List<String> orderList = new ArrayList<>();
        String customerId = customerList.entrySet().stream()
                .filter(x -> customerInput[0].equals(x.getValue().getFirstName().trim().toLowerCase()))
                .filter(x -> customerInput[1].equals(x.getValue().getLastName().trim().toLowerCase()))
                .map(x->x.getKey().toString())
                .collect(Collectors.joining());
        List<String> orderId = orderMap.entrySet().stream()
                .filter(x -> customerId.equals(x.getValue().getCustomer())).map(x->x.getKey().toString()).collect(Collectors.toList());
        shopping_cartMap.forEach((k,v)-> {
            for(String s : orderId){
                if(v.getOrderId() == Integer.parseInt(s)){
                    orderList.add(""+shoes_Map.get(v.getOrderId()));
                    }
                }
            });
        return orderList;
    }
    public List<String> getAllShoes(){
        List<Shoes> allShoes = new ArrayList<>();
        List<String> aallShoes = new ArrayList<>();
        List<Category> allCats = new ArrayList<>();
        Map<Integer, Shoes> shoeList = repository.mapShoesToHashmap();
        Map<Integer, Category> KATEGORI = repository.mapCategoriesToHashmap();
        allShoes = shoeList.entrySet().stream().map(e->e.getValue()).collect(Collectors.toList());
        allCats = KATEGORI.entrySet().stream().map(e->e.getValue()).collect(Collectors.toList());

        aallShoes.add("Finns i storlek: 35-39\nModell: " + allShoes.get(0).getModel()+ ", Färg: " + allShoes.get(0).getColor() + ", Pris: "
                + allShoes.get(0).getPrice() + ", Kategorier: " + allCats.get(0) + ", " + allCats.get(4));
        aallShoes.add("-------------\nFinns i storlek: 35-49\nModell: " + allShoes.get(11).getModel()+ ", Färg: " + allShoes.get(11).getColor() + ", Pris: "
                + allShoes.get(11).getPrice() + ", Kategorier: " + allCats.get(0) + ", " + allCats.get(6));
        aallShoes.add("Modell: " + allShoes.get(49).getModel()+ ", Färg: " + allShoes.get(49).getColor() + ", Pris: "
                + allShoes.get(49).getPrice() + ", Kategorier: " + allCats.get(1));
        aallShoes.add("Modell: " + allShoes.get(64).getModel()+ ", Färg: " + allShoes.get(64).getColor() + ", Pris: "
                + allShoes.get(64).getPrice() + ", Kategorier: " + allCats.get(1));
        aallShoes.add("Modell: " + allShoes.get(79).getModel()+ ", Färg: " + allShoes.get(79).getColor() + ", Pris: "
                + allShoes.get(79).getPrice() + ", Kategorier: " + allCats.get(3) + ", " + allCats.get(5));
        aallShoes.add("-------------\nFinns i storlek: 38\nModell: " + allShoes.get(80).getModel()+ ", Färg: " + allShoes.get(80).getColor() + ", Pris: "
                + allShoes.get(80).getPrice() + ", Kategorier: " + allCats.get(3));
        return aallShoes;
    }
    public String getLatestOrderId() {
        return repository.listAllOrders().get(repository.listAllOrders().size()-1);
    }
    public void addRate( String grade_Id, String comment, String product_Id, String customer_Id) throws SQLException {
        repository.callProcedureRate(grade_Id,comment,product_Id,customer_Id);
    }
}





