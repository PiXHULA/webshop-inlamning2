package Inlämningsuppgift2.Controller;

import Inlämningsuppgift2.Model.Model;
import Inlämningsuppgift2.Model.Repository;
import Inlämningsuppgift2.Model.models.Shoes;
import Inlämningsuppgift2.Model.models.ShoesProperties.Category_Shoe_List;
import Inlämningsuppgift2.View.View;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    View view;
    Repository repository = new Repository();
    Model model = new Model();
    List<Shoes> currentOrder = new ArrayList<>();
    List<String> currentOrderShoeIDs = new ArrayList<>();

    public Controller(View view) {
        this.view = view;
    }

    //VISNING
    public void showAllShoes() {
        view.printMessage("Se listan på skor nedan: ");
        model.getAllShoes().forEach(e->view.printMessage(e));
    }
    public Shoes returnShoeWithId(String id) {
        return model.getProductById(Integer.parseInt(id));
    }

    //LOGIK FÖR VARUKORGEN
    public void chooseShoeToAdd(String chosenModel) throws SQLException {
        String[] modelInput = chosenModel.split(",");
        String productId = "";
        if (modelInput.length != 2)
            view.printMessage("Felaktig inmatning på input -(" + chosenModel + "), ex: (Modell),(Storlek)");
        else {
            productId = model.getProductIdByModelString(modelInput[0], modelInput[1]);
            if (!productId.isEmpty()) {
                addToOrderId(productId);
                addToOrder(returnShoeWithId(productId));
                view.printMessage(returnShoeWithId(productId) + ", har lagts till i ordern.");
            } else
                view.printMessage("Skon finns inte i lager");
        }
    }
    public void addToOrder(Shoes shoe) {
        currentOrder.add(shoe);
    }
    public void addToOrderId(String shoeID) {
        currentOrderShoeIDs.add(shoeID);
    }
    public void showCurrentOrder() {
        if (currentOrder.size() > 0) {
            int price = 0;
            for (Shoes s : currentOrder) {
                view.printMessage(s.toString());
                price = price + Integer.parseInt(s.getPrice().getPrice());
            }
            view.printMessage("Sammanlagd summa: " + price + " SEK");
        } else
            view.printMessage("Du har inte lagt in något i varukorgen än");
    }
    public void confirmOrder(String customer) throws SQLException {
        String[] customerInput = customer.split(" ");
        String productId, orderId, customerId;
        customerId = model.getCustomer_IdByCustomer(customerInput[0], customerInput[1]);
        if (currentOrderShoeIDs.size() != 0) {
            orderId = null;
            for(String id : currentOrderShoeIDs){
                model.addToCart(id, orderId, customerId);
                orderId = model.getLatestOrderId();
            }
        } else
            view.printMessage("Du har inte lagt till några skor i beställningen");
    }
    public void clearCurrentOrder(String message) {
        if (message.startsWith("avbryt"))
            view.printMessage("Bokningen är avbruten");
        if (message.startsWith("bekräfta"))
            view.printMessage("Bokningen är bekräftad");
        currentOrder.clear();
        currentOrderShoeIDs.clear();
    }

    //KUNDHISTORIK
    public void showOrders(String customerName) {
        view.printMessage("Tidigare beställningar: ");
        model.getAllOrdersByCustomerName(customerName).forEach(e->view.printMessage(e));
    }

    //INLOGGNINGS LOGIK
    public boolean checkCredentials(String customerName, String customerPassword) {
        if (model.isCustomerVerified(customerName, customerPassword))
            view.printMessage("Inloggningen lyckades");
        else
            view.printMessage("Inloggningen misslyckades");

        return model.isCustomerVerified(customerName, customerPassword);

    }

    //MEDDELANDEN
    public void viewMessage(String input) {
        if(input.startsWith("Användar"))
            view.printMessage("Ange användarnamn");
        if(input.startsWith("Lösen"))
            view.printMessage("Ange lösenord");
        if (input.startsWith("Start"))
            view.printMessage("Vad vill du göra? 1) Lägg beställning, 2) Se beställningar, 3) Logga ut");
        if(input.startsWith("Välj"))
            view.printMessage("Välj funktion. Lägg till skor, Visa beställning, " +
                "Bekräfta beställning, Avbryt beställning");
        if(input.startsWith("Märke"))
            view.printMessage("Ange märke och storlek, ex Nike,37");
        if(input.startsWith("Loggat"))
            view.printMessage("Loggat ut från webshop");
        if(input.startsWith("Error"))
            view.printMessage("Angiven funktion finns inte, välj igen");

    }
}
