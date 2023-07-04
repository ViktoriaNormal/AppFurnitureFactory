package com.example.demo;

import java.sql.*;

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

    public static Component insertComponent(Connection connection, Component component) throws SQLException {
        String query = "INSERT INTO components (id_of_component, component_type, manufacturing_price, digital_code, confidentiality_level) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, component.getId_of_component());
            statement.setString(2, component.getComponent_type());
            statement.setInt(3, component.getManufacturing_price());
            statement.setInt(4, component.getDigital_code());
            statement.setInt(5, component.getConfidentiality_level());
            statement.executeUpdate();
        }
        return component;
    }

    public static Component deleteComponent(Connection connection, int id) throws SQLException {
        String query = "DELETE FROM components WHERE id_of_component = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        return null;
    }

    public static Component updateComponent(Connection connection, Component component) throws SQLException {
        String query = "UPDATE components SET component_type = ?, manufacturing_price = ?, digital_code = ?, confidentiality_level = ? WHERE id_of_component = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, component.getComponent_type());
            statement.setInt(2, component.getManufacturing_price());
            statement.setInt(3, component.getDigital_code());
            statement.setInt(4, component.getConfidentiality_level());
            statement.setInt(5, component.getId_of_component());
            statement.executeUpdate();
        }
        return component;
    }

    public static Component[] selectAllComponents(Connection connection) throws SQLException {
        String query = "SELECT * FROM components";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            Component[] components = new Component[columnCount];
            int index = 0;
            while (resultSet.next()) {
                int id = resultSet.getInt("id_of_component");
                String type = resultSet.getString("component_type");
                int price = resultSet.getInt("manufacturing_price");
                int code = resultSet.getInt("digital_code");
                int level = resultSet.getInt("confidentiality_level");
                components[index++] = new Component(id, type, price, code, level);
            }
            return components;
        }
    }

    public static Component selectComponentById(Connection connection, int id) throws SQLException {
        String query = "SELECT * FROM components WHERE id_of_component = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int componentId = resultSet.getInt("id_of_component");
                    String type = resultSet.getString("component_type");
                    int price = resultSet.getInt("manufacturing_price");
                    int code = resultSet.getInt("digital_code");
                    int level = resultSet.getInt("confidentiality_level");
                    return new Component(componentId, type, price, code, level);
                }
            }
        }
        return null;
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

