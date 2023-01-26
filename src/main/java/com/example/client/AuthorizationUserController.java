package com.example.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import static com.example.client.EnterWindowController.client;

public class AuthorizationUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authorization_button;

    @FXML
    private TextField login_field;
    @FXML
    private Label error;
    @FXML
    private PasswordField password_field;

    @FXML
    private Button registration_button;
    @FXML
    private Button back_button;
    @FXML
    void initialize() {
        authorization_button.setOnAction(Event ->
                {
                    try {
                        String login=login_field.getText();
                        String password=password_field.getText();
                        //System.out.println(login);
                        String command="authorization.user";
                        client.sendMessageToServer(command);
                        client.sendMessageToServer(login);
                        client.sendMessageToServer(password);
                        error.setOpacity(0);

                        command=client.bufferedReader.readLine();
                        System.out.println(command);
                        if(command.equals("ok"))
                        {
                            authorization_button.getScene().getWindow().hide();
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

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        registration_button.setOnAction(Event ->
                {
                    try {
                        authorization_button.getScene().getWindow().hide();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registration_user.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                        Stage stage=new Stage();
                        stage.setTitle("Регистрация пользователя");
                        stage.setScene(scene);
                        stage.show();
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
                        authorization_button.getScene().getWindow().hide();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EnterWindow.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                        Stage stage=new Stage();
                        stage.setTitle("Главное окно");
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
