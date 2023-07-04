package com.example.demo;

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

