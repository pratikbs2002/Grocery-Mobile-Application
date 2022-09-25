package com.example.admin;

public class CustomerModel {

    String CustomerName;
    public CustomerModel(){

    }
    CustomerModel(String CustomerName)
    {
        this.CustomerName = CustomerName;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }
}
