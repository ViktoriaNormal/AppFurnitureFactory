package com.example.demo;

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

