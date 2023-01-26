package com.example.client;
import javafx.beans.property.*;
public class Products {

    private int id_product;
    private String name_product;
    private String gender_product;
    private String cost_product;
    private String amount_product;


    public Products() {
        this(0,"","","","");
    }

    public Products(int id_product, String name_product, String gender_product, String cost_product, String amount_product) {
        this.id_product = id_product;
        this.name_product = name_product;
        this.gender_product = gender_product;
        this.cost_product = cost_product;
        this.amount_product = amount_product;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id_product=" + id_product +
                ", name_product='" + name_product + '\'' +
                ", gender_product='" + gender_product + '\'' +
                ", cost_product=" + cost_product +
                ", amount_product=" + amount_product +
                '}';
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getGender_product() {
        return gender_product;
    }

    public void setGender_product(String gender_product) {
        this.gender_product = gender_product;
    }

    public String getCost_product() {
        return cost_product;
    }

    public void setCost_product(String cost_product) {
        this.cost_product = cost_product;
    }

    public String getAmount_product() {
        return amount_product;
    }

    public void setAmount_product(String amount_product) {
        this.amount_product = amount_product;
    }
}

