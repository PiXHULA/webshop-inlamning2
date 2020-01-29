package Inlämningsuppgift2.View;

import Inlämningsuppgift2.Controller.Controller;

import java.sql.SQLException;
import java.util.Scanner;

public class View {
    Scanner sc;
    String customerName;
    String customerPassword;
    String inputChoice;
    String shoe;
    String state;

    public View() throws SQLException {
        Controller controller = new Controller(this);
        sc = new Scanner(System.in);
        while (true) {
            try {
                controller.viewMessage("Användar");
                customerName = sc.nextLine().toLowerCase().trim();
                controller.viewMessage("Lösen");
                customerPassword = sc.nextLine().toLowerCase().trim();
                Boolean run = true;
                if (controller.checkCredentials(customerName, customerPassword)) {
                    while (run) {
                        controller.viewMessage("Start");
                        inputChoice = sc.nextLine().toLowerCase().trim();
                        Boolean runProgram = true;
                        switch (inputChoice) {
                            case "1":
                                while (runProgram) {
                                    controller.viewMessage("Välj");
                                    inputChoice = sc.nextLine().toLowerCase().trim();
                                    if (inputChoice.startsWith("lägg")) {
                                        controller.showAllShoes();
                                        controller.viewMessage("Märke");
                                        shoe = sc.nextLine().toLowerCase().trim();
                                        controller.chooseShoeToAdd(shoe);
                                    }
                                    if (inputChoice.startsWith("visa"))
                                        controller.showCurrentOrder();
                                    if (inputChoice.startsWith("bekräfta")) {
                                        controller.confirmOrder(customerName);
                                        controller.clearCurrentOrder(inputChoice);
                                        runProgram = false;
                                    }
                                    if (inputChoice.startsWith("avbryt")) {
                                        controller.clearCurrentOrder(inputChoice);
                                        runProgram = false;
                                    }
                                }
                                break;
                            case "2":
                                controller.showOrders(customerName);
                                break;
                            case "3":
                                controller.viewMessage("Loggat");
                                run = false;
                                break;
                            default:
                                controller.viewMessage("Error");
                                break;
                        }
                    }
                } else
                    printMessage("\"" + customerName + "\" eller \"" + customerPassword + "\" var fel, vänligen skriv igen.");
            } catch (Exception e) {
                printMessage("Felaktig inmatning på användarnamn");
            }

        }
    }
            public void printMessage(String message) {
                System.out.println(message);
            }
}
