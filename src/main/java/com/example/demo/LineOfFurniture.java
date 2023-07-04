package com.example.demo;

import java.sql.*;

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

    public static LineOfFurniture insertLineOfFurniture(Connection connection, LineOfFurniture lineOfFurniture) throws SQLException {
        String query = "INSERT INTO lines_of_furniture (id_line, name, confidentiality_level) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, lineOfFurniture.getId_line());
            statement.setString(2, lineOfFurniture.getName());
            statement.setInt(3, lineOfFurniture.getConfidentiality_level());
            statement.executeUpdate();
        }
        return lineOfFurniture;
    }

    public static LineOfFurniture deleteLineOfFurniture(Connection connection, int id_line) throws SQLException {
        String query = "DELETE FROM lines_of_furniture WHERE id_line = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_line);
            statement.executeUpdate();
        }
        return null;
    }

    public static LineOfFurniture updateLineOfFurniture(Connection connection, LineOfFurniture lineOfFurniture) throws SQLException {
        String query = "UPDATE lines_of_furniture SET name = ?, confidentiality_level = ? WHERE id_line = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, lineOfFurniture.getName());
            statement.setInt(2, lineOfFurniture.getConfidentiality_level());
            statement.setInt(3, lineOfFurniture.getId_line());
            statement.executeUpdate();
        }
        return lineOfFurniture;
    }

    public static LineOfFurniture[] selectAllLinesOfFurniture(Connection connection) throws SQLException {
        String query = "SELECT * FROM lines_of_furniture";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            LineOfFurniture[] linesOfFurniture = new LineOfFurniture[columnCount];
            int index = 0;
            while (resultSet.next()) {
                int idLine = resultSet.getInt("id_line");
                String name = resultSet.getString("name");
                int level = resultSet.getInt("confidentiality_level");
                linesOfFurniture[index++] = new LineOfFurniture(idLine, name, level);
            }
            return linesOfFurniture;
        }
    }

    public static LineOfFurniture selectLineOfFurnitureById(Connection connection, int id_line) throws SQLException {
        String query = "SELECT * FROM lines_of_furniture WHERE id_line = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
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

