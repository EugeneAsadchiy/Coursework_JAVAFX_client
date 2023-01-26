package com.example.client;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static com.example.client.EnterWindowController.client;

public class RegistrationUserController {

    //public static Client client;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;
    @FXML
    private PasswordField password_field;
    @FXML
    private Label error;
    @FXML
    private Button registration_button;
    @FXML
    private Button back_button;


    @FXML
    void initialize() {
        registration_button.setOnAction(Event ->
                {
                    try {
                        String login=login_field.getText();
                        String password=password_field.getText();
                        //System.out.println(login);
                        String command="registration.user";
                        client.sendMessageToServer(command);
                        client.sendMessageToServer(login);
                        client.sendMessageToServer(password);
                        command=client.bufferedReader.readLine();
                        System.out.println(command);
                        if(command.equals("ok"))
                        {
                            registration_button.getScene().getWindow().hide();
                            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainWindow_user.fxml"));
                            Scene scene = new Scene(fxmlLoader.load(), 777, 567);
                            Stage stage=new Stage();
                            stage.setTitle("Меню пользователя");
                            stage.setScene(scene);
                            stage.show();
                        }
                        else
                        {
                            error.setOpacity(1.0);
                        }

//                AuthorizationController.
//                client.sendMessageToServer("hello");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        back_button.setOnAction(Event ->
                {
                    try {
                        registration_button.getScene().getWindow().hide();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("authorization_user.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                        Stage stage=new Stage();
                        stage.setTitle("Авторизация пользователя");
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
