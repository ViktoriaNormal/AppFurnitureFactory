package Service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LineOfFurniture {

    private int id_line;
    private String name;
    private int confidentiality_level;

    public LineOfFurniture(int id_line, String name, int confidentiality_level) {
        this.id_line = id_line;
        this.name = name;
        this.confidentiality_level = confidentiality_level;
    }

    public LineOfFurniture(int id_line, String name) {
        this.id_line = id_line;
        this.name = name;
        this.confidentiality_level = 0;
    }

    public LineOfFurniture insertLineOfFurniture() {
        String query = "INSERT INTO line_of_furniture (name) VALUES (?)";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setString(2, getName());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public LineOfFurniture deleteLineOfFurniture() {
        String query = "DELETE FROM line_of_furniture WHERE id_line = ?";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setInt(1, getId_line());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LineOfFurniture updateLineName(String new_value) {
        String query = "UPDATE line_of_furniture SET name = " + new_value + " WHERE id_line = " + getId_line();
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

//    public static LineOfFurniture[] selectAllLinesOfFurniture() {
//        String query = "SELECT * FROM line_of_furniture";
//        try {
//            PreparedStatement statement = Connector.getConnection().prepareStatement(query);
//            ResultSet resultSet = statement.executeQuery(query);
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            int columnCount = metaData.getColumnCount();
//            LineOfFurniture[] linesOfFurniture = new LineOfFurniture[columnCount];
//            int index = 0;
//            while (resultSet.next()) {
//                int idLine = resultSet.getInt("id_line");
//                String name = resultSet.getString("name");
//                int level = resultSet.getInt("confidentiality_level");
//                linesOfFurniture[index++] = new LineOfFurniture(idLine, name, level);
//            }
//            return linesOfFurniture;
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static ObservableList<LineOfFurniture> selectAllLinesOfFurniture() {
        String query = "SELECT * FROM line_of_furniture";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            ObservableList<LineOfFurniture> linesOfFurniture = FXCollections.observableArrayList();
            while (resultSet.next()) {
                int idLine = resultSet.getInt("id_line");
                String name = resultSet.getString("name");
                int level = resultSet.getInt("confidentiality_level");
                linesOfFurniture.add(new LineOfFurniture(idLine, name, level));
            }
            return linesOfFurniture;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static LineOfFurniture selectLineOfFurnitureById(int id_line) {
        String query = "SELECT * FROM line_of_furniture WHERE id_line = ?";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setInt(1, id_line);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idLine = resultSet.getInt("id_line");
                    String name = resultSet.getString("name");
                    int level = resultSet.getInt("confidentiality_level");
                    return new LineOfFurniture(idLine, name, level);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getId_line() {
        return id_line;
    }

    public void setId_line(int id_line) {
        this.id_line = id_line;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConfidentiality_level() {
        return confidentiality_level;
    }

    public void setConfidentiality_level(int confidentiality_level) {
        this.confidentiality_level = confidentiality_level;
    }
}

