package com.example.demo;

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

