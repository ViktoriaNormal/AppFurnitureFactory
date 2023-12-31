package Service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public FurnitureOrder insertFurnitureOrder() {
        String query = "INSERT INTO furniture_orders (id_of_order, id_of_piece, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setInt(1, getId_of_order());
            statement.setInt(2, getId_of_piece());
            statement.setInt(3, getQuantity());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FurnitureOrder deleteFurnitureOrder(int id_of_order, int id_of_piece) {
        String query = "DELETE FROM furniture_orders WHERE id_of_order = ? AND id_of_piece = ?";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setInt(1, id_of_order);
            statement.setInt(2, id_of_piece);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<FurnitureOrder> selectAllFurnitureOrders() {
        String query = "SELECT * FROM furniture_orders";
        try {
            PreparedStatement statement = Connector.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery(query);
            ObservableList<FurnitureOrder> furnitureOrders = FXCollections.observableArrayList();
            while (resultSet.next()) {
                int orderId = resultSet.getInt("id_of_order");
                int pieceId = resultSet.getInt("id_of_piece");
                int quantity = resultSet.getInt("quantity");
                int level = resultSet.getInt("confidentiality_level");
                furnitureOrders.add(new FurnitureOrder(Objects.requireNonNull(Order.selectOrderById(orderId)), Objects.requireNonNull(PieceOfFurniture.selectPieceOfFurnitureById(pieceId)), quantity, level));
            }
            return furnitureOrders;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static FurnitureOrder selectFurnitureOrderById(int id_of_order, int id_of_piece) {
        String query = "SELECT * FROM furniture_orders WHERE id_of_order = ? AND id_of_piece = ?";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setInt(1, id_of_order);
            statement.setInt(2, id_of_piece);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int orderId = resultSet.getInt("id_of_order");
                    int pieceId = resultSet.getInt("id_of_piece");
                    int quantity = resultSet.getInt("quantity");
                    int level = resultSet.getInt("confidentiality_level");
                    return new FurnitureOrder(Objects.requireNonNull(Order.selectOrderById(orderId)),
                            Objects.requireNonNull(PieceOfFurniture.selectPieceOfFurnitureById(pieceId)),
                            quantity, level);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
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

