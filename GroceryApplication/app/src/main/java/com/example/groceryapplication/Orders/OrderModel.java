package com.example.groceryapplication.Orders;

public class OrderModel {

    String Order_fid, Order_id, Order_date;

    public OrderModel() {

    }

    OrderModel(String Order_fid, String Order_id, String Order_date) {
        this.Order_fid = Order_fid;
        this.Order_id = Order_id;
        this.Order_date = Order_date;
    }

    public String getOrder_fid() {
        return Order_fid;
    }

    public void setOrder_fid(String order_fid) {
        Order_fid = order_fid;
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
}
