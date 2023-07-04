package com.example.demo;

import java.sql.Date;

public class Order {
    private int id_of_order;
    private int id_of_shop;
    private Date date_of_order;
    private int number_of_order;
    private int confidentiality_level;

    public Order(int id_of_order, Shop shop, Date date_of_order, int number_of_order, int confidentiality_level) {
        this.id_of_order = id_of_order;
        this.id_of_shop = shop.getId_of_shop();
        this.date_of_order = date_of_order;
        this.number_of_order = number_of_order;
        this.confidentiality_level = confidentiality_level;
    }

    public Order(int id_of_order, Shop shop, Date date_of_order, int number_of_order) {
        this.id_of_order = id_of_order;
        this.id_of_shop = shop.getId_of_shop();
        this.date_of_order = date_of_order;
        this.number_of_order = number_of_order;
        this.confidentiality_level = 0;
    }

    public int getId_of_order() {
        return id_of_order;
    }

    public void setId_of_order(int id_of_order) {
        this.id_of_order = id_of_order;
    }

    public int getId_of_shop() {
        return id_of_shop;
    }

    public void setId_of_shop(Shop shop) {
        this.id_of_shop = shop.getId_of_shop();
    }

    public Date getDate_of_order() {
        return date_of_order;
    }

    public void setDate_of_order(Date date_of_order) {
        this.date_of_order = date_of_order;
    }

    public int getNumber_of_order() {
        return number_of_order;
    }

    public void setNumber_of_order(int number_of_order) {
        this.number_of_order = number_of_order;
    }

    public int getConfidentiality_level() {
        return confidentiality_level;
    }

    public void setConfidentiality_level(int confidentiality_level) {
        this.confidentiality_level = confidentiality_level;
    }
}

