package com.example.demo;

public class Component {

    private int id_of_component;
    private String component_type;
    private int manufacturing_price;
    private int digital_code;
    private int confidentiality_level;

    public Component(int id_of_component, String component_type, int manufacturing_price, int digital_code, int confidentiality_level) {
        this.id_of_component = id_of_component;
        this.component_type = component_type;
        this.manufacturing_price = manufacturing_price;
        this.digital_code = digital_code;
        this.confidentiality_level = confidentiality_level;
    }

    public Component(int id_of_component, String component_type, int manufacturing_price, int digital_code) {
        this.id_of_component = id_of_component;
        this.component_type = component_type;
        this.manufacturing_price = manufacturing_price;
        this.digital_code = digital_code;
        this.confidentiality_level = 0;
    }

    public int getId_of_component() {
        return id_of_component;
    }

    public void setId_of_component(int id_of_component) {
        this.id_of_component = id_of_component;
    }

    public String getComponent_type() {
        return component_type;
    }

    public void setComponent_type(String component_type) {
        this.component_type = component_type;
    }

    public int getManufacturing_price() {
        return manufacturing_price;
    }

    public void setManufacturing_price(int manufacturing_price) {
        this.manufacturing_price = manufacturing_price;
    }

    public int getDigital_code() {
        return digital_code;
    }

    public void setDigital_code(int digital_code) {
        this.digital_code = digital_code;
    }

    public int getConfidentiality_level() {
        return confidentiality_level;
    }

    public void setConfidentiality_level(int confidentiality_level) {
        this.confidentiality_level = confidentiality_level;
    }
}

