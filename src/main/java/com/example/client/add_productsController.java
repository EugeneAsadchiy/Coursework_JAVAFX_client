package com.example.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.client.EnterWindowController.client;

public class add_productsController {

    private Products_view ex;

    @FXML
    private Button b_add_products;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_cost;
    @FXML
    private TextField tf_amount;
    @FXML
    private ComboBox<String> cb_gender;

    @FXML

    public void initialize() {
        cb_gender.getItems().setAll("Мужское", "Женское");
        b_add_products.setOnAction(Event ->
        {
            try {
                String name=tf_name.getText();
                String gender=cb_gender.getSelectionModel().getSelectedItem();
                String cost= tf_cost.getText();
                String amount=tf_amount.getText();
                String update = "add_products";
                client.sendMessageToServer(update);
                client.sendMessageToServer(name);
                client.sendMessageToServer(gender);
                client.sendMessageToServer(cost);
                client.sendMessageToServer(amount);
                b_add_products.getScene().getWindow().hide();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }
}
