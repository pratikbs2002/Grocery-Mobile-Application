package com.example.admin.Items;

public class ItemListModel {
    String Item_Name, Item_Weight;

    public ItemListModel() {
    }

    ItemListModel(String Item_Name, String Item_Weight) {
        this.Item_Name = Item_Name;
        this.Item_Weight = Item_Weight;
    }

    public String getItem_Name() {
        return Item_Name;
    }

    public void setItem_Name(String item_Name) {
        Item_Name = item_Name;
    }

    public String getItem_Weight() {
        return Item_Weight;
    }

    public void setItem_Weight(String item_Weight) {
        Item_Weight = item_Weight;
    }
}
