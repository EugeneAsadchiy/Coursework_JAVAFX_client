package com.example.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.client.EnterWindowController.client;

public class Users_view {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TableView<Users> tableView_Users;
    @FXML
    private TableColumn<Users,Integer> id_users;
    @FXML
    private TableColumn<Users,String> name_users;
    @FXML
    private TableColumn<Users,String> password_users;
    @FXML
    private Button btn_add_users;
    @FXML
    private Button back_button;
    @FXML
    private Button btn_dele_users;
    private final ObservableList<Users> usersList = FXCollections.observableArrayList();
    public void refresh_table()
    {
        try {
            tableView_Users.getItems().clear();
            String update = "check_users";
            client.sendMessageToServer(update);
            String[] num=null;
            while(true)
            {
                update = client.bufferedReader.readLine();
                if(update.equals("stop"))break;
                num = update.split(",");
                usersList.add(new Users(Integer.parseInt(num[0]),num[1], num[2]));
            }
            tableView_Users.setItems(usersList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize()
    {
        id_users.setCellValueFactory(new PropertyValueFactory<>("id_users"));
        name_users.setCellValueFactory(new PropertyValueFactory<>("name_users"));
        password_users.setCellValueFactory(new PropertyValueFactory<>("password_users"));
        refresh_table();

        name_users.setCellFactory(TextFieldTableCell.forTableColumn());
        password_users.setCellFactory(TextFieldTableCell.forTableColumn());

        btn_add_users.setOnAction(Event ->
                {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add_users.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 300, 200);
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setTitle("Добавление");
                        stage.setScene(scene);
                        stage.showAndWait();
                        refresh_table();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        btn_dele_users.setOnAction(Event ->
        {
            try {
                Users selectedUser = tableView_Users.getSelectionModel().getSelectedItem();
                String command = "del_users";
                client.sendMessageToServer(command);
                String id= String.valueOf(selectedUser.getId_users());
                client.sendMessageToServer(id);
                refresh_table();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        back_button.setOnAction(Event ->
                {
                    try {
                        back_button.getScene().getWindow().hide();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainWindow_admin.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                        Stage stage = new Stage();
                        stage.setTitle("Меню админа");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );


    }

    public void handleEditName(TableColumn.CellEditEvent<Users, String> usersStringCellEditEvent) throws IOException {
        TablePosition<Users, String> pos = usersStringCellEditEvent.getTablePosition();
        String newName=usersStringCellEditEvent.getNewValue();
        Users selectedProd = tableView_Users.getSelectionModel().getSelectedItem();
        client.sendMessageToServer("edit_users");
        client.sendMessageToServer("Users_name/"+newName+"/"+selectedProd.getId_users());
        int row=pos.getRow();
        Users users=usersStringCellEditEvent.getTableView().getItems().get(row);
        users.setName_users(newName);
    }

    public void handleEditPassword(TableColumn.CellEditEvent<Users, String> usersStringCellEditEvent) throws IOException {
        TablePosition<Users, String> pos = usersStringCellEditEvent.getTablePosition();
        String newPassword=usersStringCellEditEvent.getNewValue();
        Users selectedProd = tableView_Users.getSelectionModel().getSelectedItem();
        client.sendMessageToServer("edit_users");
        client.sendMessageToServer("Users_password/"+newPassword+"/"+selectedProd.getId_users());
        int row=pos.getRow();
        Users users=usersStringCellEditEvent.getTableView().getItems().get(row);
        users.setPassword_users(newPassword);
    }
}
