package Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public PieceOfFurniture insertPieceOfFurniture() {
        String query = "INSERT INTO piece_of_furniture (piece_type, article_number, price, id_line) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setString(2, getPiece_type());
            statement.setInt(3, getArticle_number());
            statement.setInt(4, getPrice());
            statement.setInt(5, getId_line());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public PieceOfFurniture deletePieceOfFurniture() {
        String query = "DELETE FROM piece_of_furniture WHERE id_of_piece = ?";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setInt(1, getId_of_piece());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PieceOfFurniture updatePieceOfFurniture(String column_name, Object new_value) {
        String query = "UPDATE piece_of_furniture SET " + column_name + " = " + new_value + " WHERE id_of_piece = " + getId_of_piece();
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            switch (column_name) {
                case "piece_type" -> setPiece_type((String) new_value);
                case "article_number" -> setArticle_number((int) new_value);
                case "price" -> setPrice((int) new_value);
                case "id_line" -> setId_line(Objects.requireNonNull(LineOfFurniture.selectLineOfFurnitureById((int) new_value)));
            }
        }
        catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }

        return this;
    }

//    public static PieceOfFurniture[] selectAllPiecesOfFurniture() {
//        String query = "SELECT * FROM piece_of_furniture";
//        try {
//            PreparedStatement statement = Connector.getConnection().prepareStatement(query);
//            ResultSet resultSet = statement.executeQuery(query);
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            int columnCount = metaData.getColumnCount();
//            PieceOfFurniture[] piecesOfFurniture = new PieceOfFurniture[columnCount];
//            int index = 0;
//            while (resultSet.next()) {
//                int idPiece = resultSet.getInt("id_of_piece");
//                String pieceType = resultSet.getString("piece_type");
//                int articleNumber = resultSet.getInt("article_number");
//                int price = resultSet.getInt("price");
//                int idLine = resultSet.getInt("id_line");
//                int level = resultSet.getInt("confidentiality_level");
//                piecesOfFurniture[index++] = new PieceOfFurniture(idPiece, pieceType, articleNumber, price,
//                        Objects.requireNonNull(LineOfFurniture.selectLineOfFurnitureById(idLine)), level);
//            }
//            return piecesOfFurniture;
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static List<PieceOfFurniture> selectAllPiecesOfFurniture() {
        String query = "SELECT * FROM piece_of_furniture";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<PieceOfFurniture> piecesOfFurniture = new ArrayList<>();
            while (resultSet.next()) {
                int idPiece = resultSet.getInt("id_of_piece");
                String pieceType = resultSet.getString("piece_type");
                int articleNumber = resultSet.getInt("article_number");
                int price = resultSet.getInt("price");
                int idLine = resultSet.getInt("id_line");
                int level = resultSet.getInt("confidentiality_level");
                piecesOfFurniture.add(new PieceOfFurniture(idPiece, pieceType, articleNumber, price,
                        Objects.requireNonNull(LineOfFurniture.selectLineOfFurnitureById(idLine)), level));
            }
            return piecesOfFurniture;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PieceOfFurniture selectPieceOfFurnitureById(int id_of_piece) {
        String query = "SELECT * FROM piece_of_furniture WHERE id_of_piece = ?";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setInt(1, id_of_piece);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idPiece = resultSet.getInt("id_of_piece");
                    String pieceType = resultSet.getString("piece_type");
                    int articleNumber = resultSet.getInt("article_number");
                    int price = resultSet.getInt("price");
                    int idLine = resultSet.getInt("id_line");
                    int level = resultSet.getInt("confidentiality_level");
                    return new PieceOfFurniture(idPiece, pieceType, articleNumber, price,
                            Objects.requireNonNull(LineOfFurniture.selectLineOfFurnitureById(idLine)), level);
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

