package com.example.client;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EnterWindowController {
    public static Client client;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enter_admin_button;

    @FXML
    private Button enter_user_button;
    private static boolean isConnect=false;
    @FXML
    void initialize() {

        try {
            if(!isConnect)
            {
                client = new Client(new Socket("localhost", 1234));
                System.out.println("Connected to server!");
                isConnect= true;
            }


        } catch (IOException e){
            e.printStackTrace();
        }
        enter_admin_button.setOnAction(Event ->
                {
                    try {
                        enter_admin_button.getScene().getWindow().hide();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("authorization_admin.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                        Stage stage = new Stage();
                        stage.setTitle("Авторизация админа");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        enter_user_button.setOnAction(Event ->
                {
                    try {
                        enter_admin_button.getScene().getWindow().hide();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("authorization_user.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                        Stage stage = new Stage();
                        stage.setTitle("Авторизация пользователя");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

}
