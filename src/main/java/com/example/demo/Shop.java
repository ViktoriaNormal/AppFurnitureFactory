package com.example.demo;

public class Shop {

    private int id_of_shop;
    private String address;
    private int fax_number;
    private int confidentiality_level;

    public Shop(int id_of_shop, String address, int fax_number, int confidentiality_level) {
        this.id_of_shop = id_of_shop;
        this.address = address;
        this.fax_number = fax_number;
        this.confidentiality_level = confidentiality_level;
    }

    public Shop(int id_of_shop, String address, int fax_number) {
        this.id_of_shop = id_of_shop;
        this.address = address;
        this.fax_number = fax_number;
        this.confidentiality_level = 0;
    }

    public int getId_of_shop() {
        return id_of_shop;
    }

    public void setId_of_shop(int id_of_shop) {
        this.id_of_shop = id_of_shop;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFax_number() {
        return fax_number;
    }

    public void setFax_number(int fax_number) {
        this.fax_number = fax_number;
    }

    public int getConfidentiality_level() {
        return confidentiality_level;
    }

    public void setConfidentiality_level(int confidentiality_level) {
        this.confidentiality_level = confidentiality_level;
    }
}

