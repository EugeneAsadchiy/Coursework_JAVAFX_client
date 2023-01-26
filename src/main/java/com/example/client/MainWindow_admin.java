package com.example.client;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow_admin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back_button;
    @FXML
    private Button all_products;
    @FXML
    public Button all_orders;
    @FXML
    public Button all_users;

    @FXML
    void initialize() {
        all_products.setOnAction(Event ->
                {
                    try {
                        all_products.getScene().getWindow().hide();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Products.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                        Stage stage = new Stage();
                        stage.setTitle("Товары на складе");
                        stage.setScene(scene);
                        stage.show();
//                AuthorizationController.
//                client.sendMessageToServer("hello");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        all_users.setOnAction(Event ->
                {
                    try {
                        all_orders.getScene().getWindow().hide();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("all_users.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                        Stage stage = new Stage();
                        stage.setTitle("Список пользователей");
                        stage.setScene(scene);
                        stage.show();
//                AuthorizationController.
//                client.sendMessageToServer("hello");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        all_orders.setOnAction(Event->
        {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("all_orders.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 751, 320);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Заказы");
                stage.setScene(scene);
                stage.showAndWait();
//                refresh_table();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        back_button.setOnAction(Event ->
                {
                    try {
                        back_button.getScene().getWindow().hide();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("authorization_admin.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                        Stage stage = new Stage();
                        stage.setTitle("Авторизация админа");
                        stage.setScene(scene);
                        stage.show();
//                AuthorizationController.
//                client.sendMessageToServer("hello");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}
