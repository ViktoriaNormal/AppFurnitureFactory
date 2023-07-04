package com.example.demo;

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

