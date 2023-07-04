package com.example.demo;

import java.sql.*;

public class Shop {

    private int id_of_shop;
    private String address;
    private int fax_number;
    private int confidentiality_level;

    public Shop(int id_of_shop, String address, int fax_number, int confidentiality_level) {
        this.id_of_shop = id_of_shop;
        this.address = address;
        this.fax_number = fax_number;
        this.confidentiality_level = confidentiality_level;
    }

    public Shop(int id_of_shop, String address, int fax_number) {
        this.id_of_shop = id_of_shop;
        this.address = address;
        this.fax_number = fax_number;
        this.confidentiality_level = 0;
    }

    public static Shop insertShop(Connection connection, Shop shop) throws SQLException {
        String query = "INSERT INTO shops (id_of_shop, address, fax_number, confidentiality_level) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, shop.getId_of_shop());
            statement.setString(2, shop.getAddress());
            statement.setInt(3, shop.getFax_number());
            statement.setInt(4, shop.getConfidentiality_level());
            statement.executeUpdate();
        }
        return shop;
    }

    public static Shop deleteShop(Connection connection, int id_of_shop) throws SQLException {
        String query = "DELETE FROM shops WHERE id_of_shop = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_of_shop);
            statement.executeUpdate();
        }
        return null;
    }

    public static Shop updateShop(Connection connection, Shop shop) throws SQLException {
        String query = "UPDATE shops SET address = ?, fax_number = ?, confidentiality_level = ? WHERE id_of_shop = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, shop.getAddress());
            statement.setInt(2, shop.getFax_number());
            statement.setInt(3, shop.getConfidentiality_level());
            statement.setInt(4, shop.getId_of_shop());
            statement.executeUpdate();
        }
        return shop;
    }

    public static Shop[] selectAllShops(Connection connection) throws SQLException {
        String query = "SELECT * FROM shops";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            Shop[] shops = new Shop[columnCount];
            int index = 0;
            while (resultSet.next()) {
                int idShop = resultSet.getInt("id_of_shop");
                String address = resultSet.getString("address");
                int faxNumber = resultSet.getInt("fax_number");
                int level = resultSet.getInt("confidentiality_level");
                shops[index++] = new Shop(idShop, address, faxNumber, level);
            }
            return shops;
        }
    }

    public static Shop selectShopById(Connection connection, int id_of_shop) throws SQLException {
        String query = "SELECT * FROM shops WHERE id_of_shop = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_of_shop);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idShop = resultSet.getInt("id_of_shop");
                    String address = resultSet.getString("address");
                    int faxNumber = resultSet.getInt("fax_number");
                    int level = resultSet.getInt("confidentiality_level");
                    return new Shop(idShop, address, faxNumber, level);
                }
            }
        }
        return null;
    }

    public int getId_of_shop() {
        return id_of_shop;
    }

    public void setId_of_shop(int id_of_shop) {
        this.id_of_shop = id_of_shop;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFax_number() {
        return fax_number;
    }

    public void setFax_number(int fax_number) {
        this.fax_number = fax_number;
    }

    public int getConfidentiality_level() {
        return confidentiality_level;
    }

    public void setConfidentiality_level(int confidentiality_level) {
        this.confidentiality_level = confidentiality_level;
    }
}

