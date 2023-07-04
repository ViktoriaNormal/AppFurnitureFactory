package com.example.demo;

import java.sql.Date;
import java.sql.*;

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

    public static Order insertOrder(Connection connection, Order order) throws SQLException {
        String query = "INSERT INTO orders (id_of_order, id_of_shop, date_of_order, number_of_order, confidentiality_level) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, order.getId_of_order());
            statement.setInt(2, order.getId_of_shop());
            statement.setDate(3, order.getDate_of_order());
            statement.setInt(4, order.getNumber_of_order());
            statement.setInt(5, order.getConfidentiality_level());
            statement.executeUpdate();
        }
        return order;
    }

    public static Order deleteOrder(Connection connection, int id_of_order) throws SQLException {
        String query = "DELETE FROM orders WHERE id_of_order = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_of_order);
            statement.executeUpdate();
        }
        return null;
    }

    public static Order updateOrder(Connection connection, Order order) throws SQLException {
        String query = "UPDATE orders SET id_of_shop = ?, date_of_order = ?, number_of_order = ?, confidentiality_level = ? WHERE id_of_order = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, order.getId_of_shop());
            statement.setDate(2, order.getDate_of_order());
            statement.setInt(3, order.getNumber_of_order());
            statement.setInt(4, order.getConfidentiality_level());
            statement.setInt(5, order.getId_of_order());
            statement.executeUpdate();
        }
        return order;
    }

    public static Order[] selectAllOrders(Connection connection) throws SQLException {
        String query = "SELECT * FROM orders";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            Order[] orders = new Order[columnCount];
            int index = 0;
            while (resultSet.next()) {
                int idOrder = resultSet.getInt("id_of_order");
                int idShop = resultSet.getInt("id_of_shop");
                Date date = resultSet.getDate("date_of_order");
                int number = resultSet.getInt("number_of_order");
                int level = resultSet.getInt("confidentiality_level");
                orders[index++] = new Order(idOrder, idShop, date, number, level);
            }
            return orders;
        }
    }

    public static Order selectOrderById(Connection connection, int id_of_order) throws SQLException {
        String query = "SELECT * FROM orders WHERE id_of_order = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_of_order);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idOrder = resultSet.getInt("id_of_order");
                    int idShop = resultSet.getInt("id_of_shop");
                    Date date = resultSet.getDate("date_of_order");
                    int number = resultSet.getInt("number_of_order");
                    int level = resultSet.getInt("confidentiality_level");
                    return new Order(idOrder, idShop, date, number, level);
                }
            }
        }
        return null;
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

