package Inlämningsuppgift2.Model;

import Inlämningsuppgift2.Model.models.*;
import Inlämningsuppgift2.Model.models.OrderProperties.Customer_order;
import Inlämningsuppgift2.Model.models.ShoesProperties.*;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

public class Repository {
    private Connection con;
    private Properties p = new Properties();

    public Repository() {
        try {
            p.load(new FileInputStream("C:\\Users\\Joakim\\Desktop\\JAVA\\database\\src\\Inlämningsuppgift2\\Settings.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, Color> mapColorsToHashmap() {
        Map<Integer, Color> colorMap = new HashMap<>();
        String query = "Select * from color";
        try (Connection con = DriverManager.getConnection
                (p.getProperty("connectionString"), p.getProperty("name"), p.getProperty("password"))) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                colorMap.put(rs.getInt("color_id"), new Color(rs.getString("color")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return colorMap;
    }
    public Map<Integer, Size> mapSizesToHashmap() {
        Map<Integer, Size> sizeMap = new HashMap<>();
        String query = "Select * from shoes_size";
        try (Connection con = DriverManager.getConnection
                (p.getProperty("connectionString"), p.getProperty("name"), p.getProperty("password"))) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                sizeMap.put(rs.getInt("shoes_size_id"), new Size(rs.getString("shoes_size")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sizeMap;
    }
    public Map<Integer, Brand> mapBrandsToHashmap() {
        Map<Integer, Brand> brandMap = new HashMap<>();
        String query = "Select * from brand";
        try (Connection con = DriverManager.getConnection
                (p.getProperty("connectionString"), p.getProperty("name"), p.getProperty("password"))) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                brandMap.put(rs.getInt("brand_id"), new Brand(rs.getString("brand_name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return brandMap;
    }
    public Map<Integer, Price> mapPricesToHashmap() {
        Map<Integer, Price> priceMap = new HashMap<>();
        String query = "Select * from price";
        try (Connection con = DriverManager.getConnection
                (p.getProperty("connectionString"), p.getProperty("name"), p.getProperty("password"))) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                priceMap.put(rs.getInt("price_id"), new Price(rs.getString("cost")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return priceMap;
    }
    public Map<Integer, Category> mapCategoriesToHashmap() {
        Map<Integer, Category> categoryMap = new HashMap<>();
        String query = "Select * from category";
        try (Connection con = DriverManager.getConnection
                (p.getProperty("connectionString"), p.getProperty("name"), p.getProperty("password"))) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                categoryMap.put(rs.getInt("category_id"), new Category(rs.getString("category_name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryMap;
    }
    public Map<Integer, Shoes> mapShoesToHashmap() {
        Map<Integer, Brand> MÄRKE = mapBrandsToHashmap();
        Map<Integer, Size> STORLEK = mapSizesToHashmap();
        Map<Integer, Color> FÄRGER = mapColorsToHashmap();
        Map<Integer, Price> PRIS = mapPricesToHashmap();
        Map<Integer, Shoes> shoesMap = new HashMap<>();
        String query = "Select * from shoes";
        try (Connection con = DriverManager.getConnection
                (p.getProperty("connectionString"), p.getProperty("name"), p.getProperty("password"))) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                shoesMap.put(rs.getInt("shoes_id"),
                        new Shoes(
                                MÄRKE.get(rs.getInt("shoes_brand")),
                                STORLEK.get(rs.getInt("shoes_size")),
                                FÄRGER.get(rs.getInt("shoes_color")),
                                PRIS.get(rs.getInt("shoes_price")),
                                rs.getInt("shoes_quantity_in_stock")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shoesMap;
    }
    public Map<Integer, Category_Shoe_List> mapShoesToCategory() {
        Map<Integer, Shoes> shoeMap = mapShoesToHashmap();
        Map<Integer, Category> categoryMap = mapCategoriesToHashmap();
        Map<Integer, Category_Shoe_List> ShoeAndCatMap = new HashMap<>();
        String query = "Select * from category_shoe_list";
        try (Connection con = DriverManager.getConnection
                (p.getProperty("connectionString"), p.getProperty("name"), p.getProperty("password"))) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ShoeAndCatMap.put(rs.getInt("list_category_shoe_id"),
                        new Category_Shoe_List(
                                categoryMap.get(rs.getInt("list_category_id")),
                                shoeMap.get(rs.getInt("list_shoes_id"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ShoeAndCatMap;
    }
    public Map<Integer, City> mapCitysToHashmap() {
        Map<Integer, City> cityMap = new HashMap<>();
        String query = "Select * from city";
        try (Connection con = DriverManager.getConnection
                (p.getProperty("connectionString"), p.getProperty("name"), p.getProperty("password"))) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                cityMap.put(rs.getInt("city_id"), new City(rs.getString("city")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityMap;
    }
    public Map<Integer, Customer> mapCustomersToHashmap() {
        Map<Integer, City> cityMap = mapCitysToHashmap();
        Map<Integer, Customer> customerMap = new HashMap<>();
        String query = "Select * from customer";
        try (Connection con = DriverManager.getConnection
                (p.getProperty("connectionString"), p.getProperty("name"), p.getProperty("password"))) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                customerMap.put(rs.getInt("customer_id"),
                        new Customer(
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("cu_password"),
                                cityMap.get(rs.getInt("city_id"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerMap;
    }
    public Map<Integer, Customer_order> mapOrdersToHashmap() {
        Map<Integer, Customer_order> orderMap = new HashMap<>();
        String query = "Select * from customer_order";
        try (Connection con = DriverManager.getConnection
                (p.getProperty("connectionString"), p.getProperty("name"), p.getProperty("password"))) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                orderMap.put(rs.getInt("Customer_Order_id"),
                        new Customer_order(
                                rs.getString("customer_order_customer_id"),
                                rs.getString("customer_order_date")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return orderMap;
    }
    public Map<Integer, Shopping_cart> mapShopping_cartToHashmap() {
        Map<Integer, Customer_order> c_orderMap = mapOrdersToHashmap();
        Map<Integer, Shoes> shoesMap = mapShoesToHashmap();
        Map<Integer, Shopping_cart> shopping_cartMap = new HashMap<>();
        String query = "Select * from shopping_cart";
        try (Connection con = DriverManager.getConnection
                (p.getProperty("connectionString"), p.getProperty("name"), p.getProperty("password"))) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                shopping_cartMap.put(rs.getInt("Shopping_cart_id"),
                        new Shopping_cart(
                                shoesMap.get(rs.getInt("Shopping_cart_shoes_id")),
                            rs.getInt("Shopping_cart_customer_order_id")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return shopping_cartMap;
    }
    public Map<Integer, Empty_in_stock> mapEmpty_in_StockToHashmap() {
        Map<Integer, Shoes> shoesMap = mapShoesToHashmap();
        Map<Integer, Empty_in_stock> empty_in_stockMap = new HashMap<>();
        String query = "Select * from empty_in_stock";
        try (Connection con = DriverManager.getConnection
                (p.getProperty("connectionString"), p.getProperty("name"), p.getProperty("password"))) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                empty_in_stockMap.put(rs.getInt("id"),
                        new Empty_in_stock(
                                shoesMap.get(rs.getInt("shoeid")),
                            rs.getString("emptyDate")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return empty_in_stockMap;
    }
    public List<showRating> listShowRatingToArrayList() {
        List<showRating> showRatingList = new ArrayList<>();
        String query = "select * from showrating";
        try (Connection con = DriverManager.getConnection
                (p.getProperty("connectionString"), p.getProperty("name"), p.getProperty("password"))) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                showRatingList.add(new showRating(rs.getString("Product")
                        ,rs.getString("rating")
                        ,rs.getString("ratingGrade")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showRatingList;
    }
    public List<String> listAllOrders(){
        List<String> orderList = new ArrayList<>();
        String query = "Select * from customer_order";
        try (Connection con = DriverManager.getConnection
                (p.getProperty("connectionString"), p.getProperty("name"), p.getProperty("password"))) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                orderList.add(rs.getString("Customer_Order_id"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return orderList;
    }
    public void callProcedureAddToCart(String product_Id, String order_Id, String customer_Id) throws SQLException {
        try (Connection con = DriverManager.getConnection
                (p.getProperty("connectionString"), p.getProperty("name"), p.getProperty("password"));) {
            CallableStatement stm = con.prepareCall("Call AddToCart(?,?,?)");
            stm.setString(1, product_Id);
            stm.setString(2, order_Id);
            stm.setString(3, customer_Id);
            stm.execute();
        }
    }
    public void callProcedureRate(String grade_Id, String comment, String product_Id, String customer_Id) throws SQLException {
        try (Connection con = DriverManager.getConnection
                (p.getProperty("connectionString"), p.getProperty("name"), p.getProperty("password"));) {
            CallableStatement stm = con.prepareCall("Call Rate(?,?,?,?)");
            stm.setString(1, grade_Id);
            stm.setString(2, comment);
            stm.setString(3, product_Id);
            stm.setString(3, customer_Id);
            stm.execute();
        }
    }
}
