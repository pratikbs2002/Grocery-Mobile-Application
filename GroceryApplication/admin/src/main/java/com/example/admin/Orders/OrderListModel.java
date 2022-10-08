package com.example.admin.Orders;

public class OrderListModel {

    String Order_fid, Order_id, Order_date, C_id;

    public OrderListModel() {

    }

    public OrderListModel(String order_fid, String order_id, String order_date, String c_id) {
        this.Order_fid = order_fid;
        this.Order_id = order_id;
        this.Order_date = order_date;
        this.C_id = c_id;
    }

    public String getC_id() {
        return C_id;
    }

    public void setC_id(String c_id) {
        C_id = c_id;
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

