package Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public FurnitureComponent insertFurnitureComponent() {
        String query = "INSERT INTO furniture_component (id_of_piece, id_of_component) VALUES (?, ?)";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setInt(1, getId_of_piece());
            statement.setInt(2, getId_of_component());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FurnitureComponent deleteFurnitureComponent() {
        String query = "DELETE FROM furniture_component WHERE id_of_piece = ? AND id_of_component = ?";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setInt(1, getId_of_piece());
            statement.setInt(2, getId_of_component());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public FurnitureComponent updateFurnitureComponentCL(int confidentiality_level) {
//        String query = "UPDATE furniture_component SET confidentiality_level = ? WHERE id_of_piece = ? AND id_of_component = ?";
//        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
//            statement.setInt(1, confidentiality_level);
//            statement.setInt(2, getId_of_piece());
//            statement.setInt(3, getId_of_component());
//            statement.executeUpdate();
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return this;
//    }

//    public static FurnitureComponent[] selectAllFurnitureComponents() {
//        String query = "SELECT * FROM furniture_component";
//        try {
//            PreparedStatement statement = Connector.getConnection().prepareStatement(query);
//            ResultSet resultSet = statement.executeQuery(query);
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            int columnCount = metaData.getColumnCount();
//            FurnitureComponent[] furnitureComponents = new FurnitureComponent[columnCount];
//            int index = 0;
//            while (resultSet.next()) {
//                int pieceId = resultSet.getInt("id_of_piece");
//                int componentId = resultSet.getInt("id_of_component");
//                int level = resultSet.getInt("confidentiality_level");
//                furnitureComponents[index++] = new FurnitureComponent(
//                        Objects.requireNonNull(PieceOfFurniture.selectPieceOfFurnitureById(pieceId)),
//                        Objects.requireNonNull(Component.selectComponentById(componentId)), level);
//            }
//            return furnitureComponents;
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static List<FurnitureComponent> selectAllFurnitureComponents() {
        String query = "SELECT * FROM furniture_component";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<FurnitureComponent> furnitureComponents = new ArrayList<>();
            while (resultSet.next()) {
                int pieceId = resultSet.getInt("id_of_piece");
                int componentId = resultSet.getInt("id_of_component");
                int level = resultSet.getInt("confidentiality_level");
                PieceOfFurniture piece = PieceOfFurniture.selectPieceOfFurnitureById(pieceId);
                Component component = Component.selectComponentById(componentId);
                furnitureComponents.add(new FurnitureComponent(piece, component, level));
            }
            return furnitureComponents;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static FurnitureComponent selectFurnitureComponentById(int id_of_piece, int id_of_component) {
        String query = "SELECT * FROM furniture_component WHERE id_of_piece = ? AND id_of_component = ?";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setInt(1, id_of_piece);
            statement.setInt(2, id_of_component);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int pieceId = resultSet.getInt("id_of_piece");
                    int componentId = resultSet.getInt("id_of_component");
                    int level = resultSet.getInt("confidentiality_level");
                    return new FurnitureComponent(Objects.requireNonNull(PieceOfFurniture.selectPieceOfFurnitureById(pieceId)),
                            Objects.requireNonNull(Component.selectComponentById(componentId)), level);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
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

