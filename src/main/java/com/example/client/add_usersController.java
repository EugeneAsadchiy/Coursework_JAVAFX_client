package com.example.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.example.client.EnterWindowController.client;

public class add_usersController {
    @FXML
    public TextField tf_name;
    @FXML
    public TextField tf_password;
    @FXML
    public Button b_add_user;
    @FXML
    public void initialize() {

        b_add_user.setOnAction(Event ->
        {
            try {
                String name=tf_name.getText();
                String password= tf_password.getText();


                String update = "add_users";
                client.sendMessageToServer(update);
                client.sendMessageToServer(name);
                client.sendMessageToServer(password);

                b_add_user.getScene().getWindow().hide();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }
}
