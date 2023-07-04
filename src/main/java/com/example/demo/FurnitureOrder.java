package com.example.demo;

import java.sql.*;

public class FurnitureOrder {

    private int id_of_order;
    private int id_of_piece;
    private int quantity;
    private int confidentiality_level;

    public FurnitureOrder(Order order, PieceOfFurniture piece, int quantity, int confidentiality_level) {
        this.id_of_order = order.getId_of_order();
        this.id_of_piece = piece.getId_of_piece();
        this.quantity = quantity;
        this.confidentiality_level = confidentiality_level;
    }

    public FurnitureOrder(Order order, PieceOfFurniture piece, int quantity) {
        this.id_of_order = order.getId_of_order();
        this.id_of_piece = piece.getId_of_piece();
        this.quantity = quantity;
        this.confidentiality_level = 0;
    }

    public static FurnitureOrder insertFurnitureOrder(Connection connection, FurnitureOrder furnitureOrder) throws SQLException {
        String query = "INSERT INTO furniture_orders (id_of_order, id_of_piece, quantity, confidentiality_level) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, furnitureOrder.getId_of_order());
            statement.setInt(2, furnitureOrder.getId_of_piece());
            statement.setInt(3, furnitureOrder.getQuantity());
            statement.setInt(4, furnitureOrder.getConfidentiality_level());
            statement.executeUpdate();
        }
        return furnitureOrder;
    }

    public static FurnitureOrder deleteFurnitureOrder(Connection connection, int id_of_order, int id_of_piece) throws SQLException {
        String query = "DELETE FROM furniture_orders WHERE id_of_order = ? AND id_of_piece = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_of_order);
            statement.setInt(2, id_of_piece);
            statement.executeUpdate();
        }
        return null;
    }

    public static FurnitureOrder updateFurnitureOrder(Connection connection, FurnitureOrder furnitureOrder) throws SQLException {
        String query = "UPDATE furniture_orders SET quantity = ?, confidentiality_level = ? WHERE id_of_order = ? AND id_of_piece = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, furnitureOrder.getQuantity());
            statement.setInt(2, furnitureOrder.getConfidentiality_level());
            statement.setInt(3, furnitureOrder.getId_of_order());
            statement.setInt(4, furnitureOrder.getId_of_piece());
            statement.executeUpdate();
        }
        return furnitureOrder;
    }

    public static FurnitureOrder[] selectAllFurnitureOrders(Connection connection) throws SQLException {
        String query = "SELECT * FROM furniture_orders";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            FurnitureOrder[] furnitureOrders = new FurnitureOrder[columnCount];
            int index = 0;
            while (resultSet.next()) {
                int orderId = resultSet.getInt("id_of_order");
                int pieceId = resultSet.getInt("id_of_piece");
                int quantity = resultSet.getInt("quantity");
                int level = resultSet.getInt("confidentiality_level");
                furnitureOrders[index++] = new FurnitureOrder(orderId, pieceId, quantity, level);
            }
            return furnitureOrders;
        }
    }

    public static FurnitureOrder selectFurnitureOrderById(Connection connection, int id_of_order, int id_of_piece) throws SQLException {
        String query = "SELECT * FROM furniture_orders WHERE id_of_order = ? AND id_of_piece = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_of_order);
            statement.setInt(2, id_of_piece);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int orderId = resultSet.getInt("id_of_order");
                    int pieceId = resultSet.getInt("id_of_piece");
                    int quantity = resultSet.getInt("quantity");
                    int level = resultSet.getInt("confidentiality_level");
                    return new FurnitureOrder(orderId, pieceId, quantity, level);
                }
            }
        }
        return null;
    }

    public int getId_of_order() {
        return id_of_order;
    }

    public void setId_of_order(Order order) {
        this.id_of_order = order.getId_of_order();
    }

    public int getId_of_piece() {
        return id_of_piece;
    }

    public void setId_of_piece(PieceOfFurniture piece) {
        this.id_of_piece = piece.getId_of_piece();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getConfidentiality_level() {
        return confidentiality_level;
    }

    public void setConfidentiality_level(int confidentiality_level) {
        this.confidentiality_level = confidentiality_level;
    }
}

