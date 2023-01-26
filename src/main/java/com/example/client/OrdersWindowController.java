package com.example.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

import static com.example.client.EnterWindowController.client;

public class OrdersWindowController {
    @FXML
    public TableView<Products> tableView_Products;
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
    private Button b_exit;
    @FXML
    private Button b_del_order;
    private final ObservableList<Products> productList = FXCollections.observableArrayList();

    public void refresh_table()
    {
        try {
            tableView_Products.getItems().clear();
            String update = "check_orders_user";
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
    void initialize()
    {
        id_product.setCellValueFactory(new PropertyValueFactory<>("id_product"));
        name_product.setCellValueFactory(new PropertyValueFactory<>("name_product"));
        gender_product.setCellValueFactory(new PropertyValueFactory<>("gender_product"));
        cost_product.setCellValueFactory(new PropertyValueFactory<>("cost_product"));
        amount_product.setCellValueFactory(new PropertyValueFactory<>("amount_product"));
        refresh_table();
        b_del_order.setOnAction(Event->
        {
            try {
                Products selectedProd = tableView_Products.getSelectionModel().getSelectedItem();
                String command = "del_orders";
                client.sendMessageToServer(command);
                String id= String.valueOf(selectedProd.getId_product());
                client.sendMessageToServer(id);
                refresh_table();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        b_exit.setOnAction(Event->
        {
            b_exit.getScene().getWindow().hide();
        });
    }
}
