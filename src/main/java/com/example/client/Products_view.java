package com.example.client;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
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

import static com.example.client.EnterWindowController.client;

public class Products_view{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
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
    private Button btn_add_products;
    @FXML
    public Button btn_dele_products;
    @FXML
    public Button btn_edit_products;
    @FXML
    private Button back_button;
    @FXML
    private final Products[] PRODUCT_ITEMS=
            {
//                    new Products(25,"Кеды", "Женское",25,50),
//                    new Products(64,"Ботинки", "Мужское",32,21),
//                    new Products(12,"Туфли", "Мужское",51,8)
            };
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
    public void initialize(){
        id_product.setCellValueFactory(new PropertyValueFactory<>("id_product"));
        name_product.setCellValueFactory(new PropertyValueFactory<>("name_product"));
        gender_product.setCellValueFactory(new PropertyValueFactory<>("gender_product"));
        cost_product.setCellValueFactory(new PropertyValueFactory<>("cost_product"));
        amount_product.setCellValueFactory(new PropertyValueFactory<>("amount_product"));
        refresh_table();

        name_product.setCellFactory(TextFieldTableCell.forTableColumn());
        gender_product.setCellFactory(TextFieldTableCell.forTableColumn());
        cost_product.setCellFactory(TextFieldTableCell.forTableColumn());
        amount_product.setCellFactory(TextFieldTableCell.forTableColumn());
        btn_add_products.setOnAction(Event ->
                {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add_products.fxml"));
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
        btn_dele_products.setOnAction(Event ->
        {
            try {
                Products selectedProd = tableView_Products.getSelectionModel().getSelectedItem();
                String command = "del_products";
                client.sendMessageToServer(command);
                String id= String.valueOf(selectedProd.getId_product());
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

    public void handleEditName(TableColumn.CellEditEvent<Products, String> productsStringCellEditEvent) throws IOException {
        //return the position
        TablePosition<Products, String> pos = productsStringCellEditEvent.getTablePosition();
        String newName=productsStringCellEditEvent.getNewValue();
        Products selectedProd = tableView_Products.getSelectionModel().getSelectedItem();
        client.sendMessageToServer("edit_products");
        client.sendMessageToServer("Products_name/"+newName+"/"+selectedProd.getId_product());
        int row=pos.getRow();
        Products products=productsStringCellEditEvent.getTableView().getItems().get(row);
        products.setName_product(newName);
    }

    public void handleEditGender(TableColumn.CellEditEvent<Products, String> productsStringCellEditEvent) throws IOException {
        TablePosition<Products, String> pos = productsStringCellEditEvent.getTablePosition();
        String newGender=productsStringCellEditEvent.getNewValue();
        Products selectedProd = tableView_Products.getSelectionModel().getSelectedItem();
        client.sendMessageToServer("edit_products");
        client.sendMessageToServer("Products_gender/"+newGender+"/"+selectedProd.getId_product());
        int row=pos.getRow();
        Products products=productsStringCellEditEvent.getTableView().getItems().get(row);
        products.setGender_product(newGender);
    }

    public void handleEditCost(TableColumn.CellEditEvent<Products, String> productsStringCellEditEvent) throws IOException {
        TablePosition<Products, String> pos = productsStringCellEditEvent.getTablePosition();
        String newCost=productsStringCellEditEvent.getNewValue();
        Products selectedProd = tableView_Products.getSelectionModel().getSelectedItem();
        client.sendMessageToServer("edit_products");
        client.sendMessageToServer("Products_cost/"+newCost+"/"+selectedProd.getId_product());
        int row=pos.getRow();
        Products products=productsStringCellEditEvent.getTableView().getItems().get(row);
        products.setCost_product(newCost);
    }

    public void handleEditAmount(TableColumn.CellEditEvent<Products, String> productsStringCellEditEvent) throws IOException {
        TablePosition<Products, String> pos = productsStringCellEditEvent.getTablePosition();
        String newAmount=productsStringCellEditEvent.getNewValue();
        Products selectedProd = tableView_Products.getSelectionModel().getSelectedItem();
        client.sendMessageToServer("edit_products");
        client.sendMessageToServer("Products_amount/"+newAmount+"/"+selectedProd.getId_product());
        int row=pos.getRow();
        Products products=productsStringCellEditEvent.getTableView().getItems().get(row);
        products.setAmount_product(newAmount);
    }
}
