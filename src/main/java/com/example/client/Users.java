package com.example.client;

public class Users {
    private int id_users;
    private String name_users;
    private String password_users;


    public Users() {
        this(0, "", "");
    }

    public Users(int id_users, String name_users, String password_users) {
        this.id_users = id_users;
        this.name_users = name_users;
        this.password_users = password_users;
    }

    public int getId_users() {
        return id_users;
    }

    public void setId_users(int id_users) {
        this.id_users = id_users;
    }

    public String getName_users() {
        return name_users;
    }

    public void setName_users(String name_users) {
        this.name_users = name_users;
    }

    public String getPassword_users() {
        return password_users;
    }

    public void setPassword_users(String password_users) {
        this.password_users = password_users;
    }
}
