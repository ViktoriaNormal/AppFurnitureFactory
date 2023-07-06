package Service;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public Component(String component_type, int manufacturing_price, int digital_code) {
        this.component_type = component_type;
        this.manufacturing_price = manufacturing_price;
        this.digital_code = digital_code;
        this.confidentiality_level = 0;
    }

    public Component insertComponent() {
        String query = "INSERT INTO component (component_type, manufacturing_price, digital_code) VALUES (?, ?, ?)";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setString(1, getComponent_type());
            statement.setInt(2, getManufacturing_price());
            statement.setInt(3, getDigital_code());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Component deleteComponent() {
        String query = "DELETE FROM component WHERE id_of_component = ?";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setInt(1, getId_of_component());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Component updateComponentDig(String column_name, Object new_value) {
        String query = "UPDATE component SET " + column_name + " = " + new_value + " WHERE id_of_component = " + getId_of_component();
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            switch (column_name) {
                case "component_type" -> setComponent_type((String) new_value);
                case "manufacturing_price" -> setManufacturing_price((int) new_value);
                case "digital_code" -> setDigital_code((int) new_value);
            }
        }
        catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }

        return this;
    }

    public Component updateComponentST(String column_name, Object new_value) {
        String query = "UPDATE component SET '" + column_name + "' = " + new_value + " WHERE id_of_component = " + getId_of_component();
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            switch (column_name) {
                case "component_type" -> setComponent_type((String) new_value);
                case "manufacturing_price" -> setManufacturing_price((int) new_value);
                case "digital_code" -> setDigital_code((int) new_value);
            }
        }
        catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }

        return this;
    }

    public static ObservableList<Component> selectAllComponents() throws SQLException {
        String query = "SELECT * FROM component";
        PreparedStatement statement = Connector.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            ObservableList<Component> components = FXCollections.observableArrayList();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_of_component");
                String type = resultSet.getString("component_type");
                int price = resultSet.getInt("manufacturing_price");
                int code = resultSet.getInt("digital_code");
                int level = resultSet.getInt("confidentiality_level");
                components.add(new Component(id, type, price, code, level));
            }
            return components;
    }

    public static Component selectComponentById(int id_of_component) {
        String query = "SELECT * FROM component WHERE id_of_component = ?";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setInt(1, id_of_component);
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
        catch (SQLException e) {
            e.printStackTrace();
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

