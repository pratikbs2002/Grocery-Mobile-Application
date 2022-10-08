package com.example.admin.Customers;

public class CustomerModel {
    String C_Name, C_Mobile, C_Id;


    public CustomerModel(String c_Name, String c_Mobile, String c_Id) {
        C_Name = c_Name;
        C_Mobile = c_Mobile;
        C_Id = c_Id;
    }

    public String getC_Id() {
        return C_Id;
    }

    public void setC_Id(String c_Id) {
        C_Id = c_Id;
    }

    public String getC_Name() {
        return C_Name;
    }

    public void setC_Name(String c_Name) {
        C_Name = c_Name;
    }

    public String getC_Mobile() {
        return C_Mobile;
    }

    public void setC_Mobile(String c_Mobile) {
        C_Mobile = c_Mobile;
    }
}
