package com.example.admin;

public class OrderModel {

    String Order_id, Order_date, Order_list;

    public OrderModel() {

    }

    OrderModel(String Order_id, String Order_date, String Order_list) {
        this.Order_id = Order_id;
        this.Order_date = Order_date;
        this.Order_list = Order_list;

    }

    public String getOrder_id() {
        return Order_id;
    }

    public void setOrder_id(String order_id) {
        Order_id = order_id;
    }

    public String getOrder_date() {
        return Order_date;
    }

    public void setOrder_date(String order_date) {
        Order_date = order_date;
    }

    public String getOrder_list() {
        return Order_list;
    }

    public void setOrder_list(String order_list) {
        Order_list = order_list;
    }
}
