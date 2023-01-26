package com.example.client;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static com.example.client.EnterWindowController.client;

public class MainWindow_user {
    @FXML
    public Label amount_error;

    @FXML
    private TableView<Products> tableView_Products;
    @FXML
    private TableColumn<Products, Integer> id_product;
    @FXML
    private TableColumn<Products,String> name_product;
    @FXML
    private TableColumn<Products,String> gender_product;
    @FXML
    private TableColumn<Products, String> cost_product;
    @FXML
    private TableColumn<Products, String> amount_product;
    @FXML
    private Button view_orders;
    @FXML
    private Button order_button;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back_button;
    private final ObservableList<Products> productList = FXCollections.observableArrayList();
    public void refresh_table()
    {
        try {
            tableView_Products.getItems().clear();
            String update = "check_products";
            client.sendMessageToServer(update);
            String[] num=null;
            while(true)
            {
                update = client.bufferedReader.readLine();
                if(update.equals("stop"))break;
                num = update.split(",");
                productList.add(new Products(Integer.parseInt(num[0]),num[1], num[2],num[3],num[4]));
            }
            tableView_Products.setItems(productList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void initialize() {
        id_product.setCellValueFactory(new PropertyValueFactory<>("id_product"));
        name_product.setCellValueFactory(new PropertyValueFactory<>("name_product"));
        gender_product.setCellValueFactory(new PropertyValueFactory<>("gender_product"));
        cost_product.setCellValueFactory(new PropertyValueFactory<>("cost_product"));
        amount_product.setCellValueFactory(new PropertyValueFactory<>("amount_product"));
        refresh_table();

        order_button.setOnAction(Event->
        {
            Products selectedProd = tableView_Products.getSelectionModel().getSelectedItem();
            if(tableView_Products.getSelectionModel().getSelectedItem()==null)
            {
                amount_error.setText("Выберите товар");
                amount_error.setOpacity(1);
                return;
            }
            try {
                amount_error.setOpacity(0);
                TextInputDialog tiDialog = new TextInputDialog();
                tiDialog.setTitle("Заказ");
                tiDialog.setHeaderText("Введите желаемое количество:");
//                tiDialog.setContentText("name");
                Optional<String> result = tiDialog.showAndWait();
                if(result.isPresent())
                {
//                    Products selectedProd = tableView_Products.getSelectionModel().getSelectedItem();
                    if(Integer.parseInt(result.get()) < Integer.parseInt(selectedProd.getAmount_product()))
                    {
                        String command = "add_orders";
                        client.sendMessageToServer(command);
                        client.sendMessageToServer(selectedProd.getName_product()+"/"+selectedProd.getGender_product()+"/"+
                                selectedProd.getCost_product()+"/"+result.get());
                    }
                    else
                    {
                        amount_error.setText("Недостаточно товаров");
                        amount_error.setOpacity(1);
                    }

                }
                refresh_table();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        view_orders.setOnAction(Event->
        {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view_orders.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 600, 305);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setTitle("Заказы");
                    stage.setScene(scene);
                    stage.showAndWait();
                    refresh_table();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        });

        back_button.setOnAction(Event ->
                {
                    try {
                        back_button.getScene().getWindow().hide();
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

