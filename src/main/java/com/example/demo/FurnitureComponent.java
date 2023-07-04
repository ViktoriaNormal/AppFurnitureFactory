package com.example.demo;

import java.sql.*;

public class FurnitureComponent {

    private int id_of_piece;
    private int id_of_component;
    private int confidentiality_level;

    public FurnitureComponent(PieceOfFurniture pieceOfFurniture, Component component, int confidentiality_level) {
        this.id_of_piece = pieceOfFurniture.getId_of_piece();
        this.id_of_component = component.getId_of_component();
        this.confidentiality_level = confidentiality_level;
    }

    public FurnitureComponent(PieceOfFurniture pieceOfFurniture, Component component) {
        this.id_of_piece = pieceOfFurniture.getId_of_piece();
        this.id_of_component = component.getId_of_component();
        this.confidentiality_level = 0;
    }

    public static FurnitureComponent insertFurnitureComponent(Connection connection, FurnitureComponent furnitureComponent) throws SQLException {
        String query = "INSERT INTO furniture_components (id_of_piece, id_of_component, confidentiality_level) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, furnitureComponent.getId_of_piece());
            statement.setInt(2, furnitureComponent.getId_of_component());
            statement.setInt(3, furnitureComponent.getConfidentiality_level());
            statement.executeUpdate();
        }
        return furnitureComponent;
    }

    public static FurnitureComponent deleteFurnitureComponent(Connection connection, int id_of_piece, int id_of_component) throws SQLException {
        String query = "DELETE FROM furniture_components WHERE id_of_piece = ? AND id_of_component = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_of_piece);
            statement.setInt(2, id_of_component);
            statement.executeUpdate();
        }
        return null;
    }

    public static FurnitureComponent updateFurnitureComponent(Connection connection, FurnitureComponent furnitureComponent) throws SQLException {
        String query = "UPDATE furniture_components SET confidentiality_level = ? WHERE id_of_piece = ? AND id_of_component = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, furnitureComponent.getConfidentiality_level());
            statement.setInt(2, furnitureComponent.getId_of_piece());
            statement.setInt(3, furnitureComponent.getId_of_component());
            statement.executeUpdate();
        }
        return furnitureComponent;
    }

    public static FurnitureComponent[] selectAllFurnitureComponents(Connection connection) throws SQLException {
        String query = "SELECT * FROM furniture_components";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            FurnitureComponent[] furnitureComponents = new FurnitureComponent[columnCount];
            int index = 0;
            while (resultSet.next()) {
                int pieceId = resultSet.getInt("id_of_piece");
                int componentId = resultSet.getInt("id_of_component");
                int level = resultSet.getInt("confidentiality_level");
                furnitureComponents[index++] = new FurnitureComponent(pieceId, componentId, level);
            }
            return furnitureComponents;
        }
    }

    public static FurnitureComponent selectFurnitureComponentById(Connection connection, int id_of_piece, int id_of_component) throws SQLException {
        String query = "SELECT * FROM furniture_components WHERE id_of_piece = ? AND id_of_component = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_of_piece);
            statement.setInt(2, id_of_component);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int pieceId = resultSet.getInt("id_of_piece");
                    int componentId = resultSet.getInt("id_of_component");
                    int level = resultSet.getInt("confidentiality_level");
                    return new FurnitureComponent(pieceId, componentId, level);
                }
            }
        }
        return null;
    }

    public int getId_of_piece() {
        return id_of_piece;
    }

    public void setId_of_piece(PieceOfFurniture piece) {
        this.id_of_piece = piece.getId_of_piece();
    }

    public int getId_of_component() {
        return id_of_component;
    }

    public void setId_of_component(Component component) {
        this.id_of_component = component.getId_of_component();
    }

    public int getConfidentiality_level() {
        return confidentiality_level;
    }

    public void setConfidentiality_level(int confidentiality_level) {
        this.confidentiality_level = confidentiality_level;
    }
}

