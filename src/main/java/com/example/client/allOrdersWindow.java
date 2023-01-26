package com.example.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;

import static com.example.client.EnterWindowController.client;

public class allOrdersWindow {
    @FXML
    public TableView<Orders> tableView_Products;
    @FXML
    public DatePicker first_date;
    @FXML
    public DatePicker second_date;
    public TextField output_profit;
    @FXML
    private TableColumn<Orders, Integer> id_product;
    @FXML
    private TableColumn<Orders,String> name_product;
    @FXML
    private TableColumn<Orders,String> gender_product;
    @FXML
    private TableColumn<Orders, String> cost_product;
    @FXML
    private TableColumn<Orders, String> amount_product;
    @FXML
    private TableColumn<Orders, Integer> id_users;
    @FXML
    private TableColumn<Orders, String> date_orders;
    @FXML
    private Button b_calculate_profit;
    @FXML
    private Button b_exit;
    @FXML
    private Button b_del_order;
    private final ObservableList<Orders> ordersList = FXCollections.observableArrayList();

    public void refresh_table()
    {
        try {
            tableView_Products.getItems().clear();
            String update = "check_orders_admin";
            client.sendMessageToServer(update);
            String[] num=null;
            while(true)
            {
                update = client.bufferedReader.readLine();
                if(update.equals("stop"))break;
                num = update.split(",");
                ordersList.add(new Orders(Integer.parseInt(num[0]),num[1], num[2],num[3],num[4],Integer.parseInt(num[5]), num[6]));
            }
            tableView_Products.setItems(ordersList);
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
        id_users.setCellValueFactory(new PropertyValueFactory<>("idUsers"));
        date_orders.setCellValueFactory(new PropertyValueFactory<>("date_orders"));
        refresh_table();
        b_calculate_profit.setOnAction(Event ->
        {

            String firstdate = String.valueOf(first_date.getValue());
            String seconddate = String.valueOf(second_date.getValue());
            if(firstdate.equals("null") && seconddate.equals("null")) {
                System.out.println("вообще ничего и выводится за все время");
                try {
                    String command = "calculate_profit_all";
                    client.sendMessageToServer(command);

                    command = client.bufferedReader.readLine();
                    output_profit.setText(command);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (firstdate.equals("null") || seconddate.equals("null"))
            {
                System.out.println("ошибка ничего не выводится");
            }
            else{
                System.out.println("выводится за промежуток");
                String[] num=null;
                num = firstdate.split("-");
                String[] num2=null;
                num2 = seconddate.split("-");
                System.out.println(firstdate);
                System.out.println(seconddate);
                System.out.println(num[2]+"."+num[1]+"."+num[0]);
                System.out.println(num2[2]+"."+num2[1]+"."+num2[0]);
                String date1=num[2]+"."+num[1]+"."+num[0];
                String date2=num2[2]+"."+num2[1]+"."+num2[0];

                try {
                String command = "calculate_profit_date";
                client.sendMessageToServer(command);
                client.sendMessageToServer(firstdate+"/"+seconddate);
                command = client.bufferedReader.readLine();
                output_profit.setText(command);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            }


        });

        b_del_order.setOnAction(Event->
        {
            try {
                Orders selectedProd = tableView_Products.getSelectionModel().getSelectedItem();
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
