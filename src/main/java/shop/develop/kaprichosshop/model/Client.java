package shop.develop.kaprichosshop.model;

import javafx.beans.value.ObservableValue;

public class Client {
    protected String id;
    private String name;
    private String lastName;
    private String address;
    private String phone;

    public Client(String id, String name, String lastName, String phone, String address) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void listClient(){

    }
}