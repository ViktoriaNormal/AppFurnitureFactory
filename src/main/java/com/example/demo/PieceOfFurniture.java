package com.example.demo;

import java.sql.*;

public class PieceOfFurniture {

    private int id_of_piece;
    private String piece_type;
    private int article_number;
    private int price;
    private int id_line;
    private int confidentiality_level;

    public PieceOfFurniture(int id_of_piece, String piece_type, int article_number, int price, LineOfFurniture lineOfFurniture,
                            int confidentiality_level) {
        this.id_of_piece = id_of_piece;
        this.piece_type = piece_type;
        this.article_number = article_number;
        this.price = price;
        this.id_line = lineOfFurniture.getId_line();
        this.confidentiality_level = confidentiality_level;
    }

    public PieceOfFurniture(int id_of_piece, String piece_type, int article_number, int price, LineOfFurniture lineOfFurniture) {
        this.id_of_piece = id_of_piece;
        this.piece_type = piece_type;
        this.article_number = article_number;
        this.price = price;
        this.id_line = lineOfFurniture.getId_line();
        this.confidentiality_level = 0;
    }

    public static PieceOfFurniture insertPieceOfFurniture(Connection connection, PieceOfFurniture pieceOfFurniture) throws SQLException {
        String query = "INSERT INTO pieces_of_furniture (id_of_piece, piece_type, article_number, price, id_line, confidentiality_level) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, pieceOfFurniture.getId_of_piece());
            statement.setString(2, pieceOfFurniture.getPiece_type());
            statement.setInt(3, pieceOfFurniture.getArticle_number());
            statement.setInt(4, pieceOfFurniture.getPrice());
            statement.setInt(5, pieceOfFurniture.getId_line());
            statement.setInt(6, pieceOfFurniture.getConfidentiality_level());
            statement.executeUpdate();
        }
        return pieceOfFurniture;
    }

    public static PieceOfFurniture deletePieceOfFurniture(Connection connection, int id_of_piece) throws SQLException {
        String query = "DELETE FROM pieces_of_furniture WHERE id_of_piece = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_of_piece);
            statement.executeUpdate();
        }
        return null;
    }

    public static PieceOfFurniture updatePieceOfFurniture(Connection connection, PieceOfFurniture pieceOfFurniture) throws SQLException {
        String query = "UPDATE pieces_of_furniture SET piece_type = ?, article_number = ?, price = ?, id_line = ?, confidentiality_level = ? WHERE id_of_piece = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, pieceOfFurniture.getPiece_type());
            statement.setInt(2, pieceOfFurniture.getArticle_number());
            statement.setInt(3, pieceOfFurniture.getPrice());
            statement.setInt(4, pieceOfFurniture.getId_line());
            statement.setInt(5, pieceOfFurniture.getConfidentiality_level());
            statement.setInt(6, pieceOfFurniture.getId_of_piece());
            statement.executeUpdate();
        }
        return pieceOfFurniture;
    }

    public static PieceOfFurniture[] selectAllPiecesOfFurniture(Connection connection) throws SQLException {
        String query = "SELECT * FROM pieces_of_furniture";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            PieceOfFurniture[] piecesOfFurniture = new PieceOfFurniture[columnCount];
            int index = 0;
            while (resultSet.next()) {
                int idPiece = resultSet.getInt("id_of_piece");
                String pieceType = resultSet.getString("piece_type");
                int articleNumber = resultSet.getInt("article_number");
                int price = resultSet.getInt("price");
                int idLine = resultSet.getInt("id_line");
                int level = resultSet.getInt("confidentiality_level");
                piecesOfFurniture[index++] = new PieceOfFurniture(idPiece, pieceType, articleNumber, price, idLine, level);
            }
            return piecesOfFurniture;
        }
    }

    public static PieceOfFurniture selectPieceOfFurnitureById(Connection connection, int id_of_piece) throws SQLException {
        String query = "SELECT * FROM pieces_of_furniture WHERE id_of_piece = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_of_piece);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idPiece = resultSet.getInt("id_of_piece");
                    String pieceType = resultSet.getString("piece_type");
                    int articleNumber = resultSet.getInt("article_number");
                    int price = resultSet.getInt("price");
                    int idLine = resultSet.getInt("id_line");
                    int level = resultSet.getInt("confidentiality_level");
                    return new PieceOfFurniture(idPiece, pieceType, articleNumber, price, idLine, level);
                }
            }
        }
        return null;
    }

    public int getId_of_piece() {
        return id_of_piece;
    }

    public void setId_of_piece(int id_of_piece) {
        this.id_of_piece = id_of_piece;
    }

    public String getPiece_type() {
        return piece_type;
    }

    public void setPiece_type(String piece_type) {
        this.piece_type = piece_type;
    }

    public int getArticle_number() {
        return article_number;
    }

    public void setArticle_number(int article_number) {
        this.article_number = article_number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId_line() {
        return id_line;
    }

    public void setId_line(LineOfFurniture line) {
        this.id_line = line.getId_line();
    }

    public int getConfidentiality_level() {
        return confidentiality_level;
    }

    public void setConfidentiality_level(int confidentiality_level) {
        this.confidentiality_level = confidentiality_level;
    }
}

