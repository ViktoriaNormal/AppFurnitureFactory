package Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public Shop insertShop() {
        String query = "INSERT INTO shop (address, fax_number) VALUES (?, ?)";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setString(2, getAddress());
            statement.setInt(3, getFax_number());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Shop deleteShop() {
        String query = "DELETE FROM shop WHERE id_of_shop = ?";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setInt(1, getId_of_shop());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Shop updateShop(String column_name, Object new_value) {
        String query = "UPDATE shop SET " + column_name + " = " + new_value + " WHERE id_of_shop = " + getId_of_shop();
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            switch (column_name) {
                case "address" -> setAddress((String) new_value);
                case "fax_number" -> setFax_number((int) new_value);
            }
        }
        catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }

        return this;
    }

//    public static Shop[] selectAllShops() {
//        String query = "SELECT * FROM shop";
//        try {
//            PreparedStatement statement = Connector.getConnection().prepareStatement(query);
//            ResultSet resultSet = statement.executeQuery(query);
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            int columnCount = metaData.getColumnCount();
//            Shop[] shops = new Shop[columnCount];
//            int index = 0;
//            while (resultSet.next()) {
//                int idShop = resultSet.getInt("id_of_shop");
//                String address = resultSet.getString("address");
//                int faxNumber = resultSet.getInt("fax_number");
//                int level = resultSet.getInt("confidentiality_level");
//                shops[index++] = new Shop(idShop, address, faxNumber, level);
//            }
//            return shops;
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static List<Shop> selectAllShops() {
        String query = "SELECT * FROM shop";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<Shop> shops = new ArrayList<>();
            while (resultSet.next()) {
                int idShop = resultSet.getInt("id_of_shop");
                String address = resultSet.getString("address");
                int faxNumber = resultSet.getInt("fax_number");
                int level = resultSet.getInt("confidentiality_level");
                shops.add(new Shop(idShop, address, faxNumber, level));
            }
            return shops;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Shop selectShopById(int id_of_shop) {
        String query = "SELECT * FROM shop WHERE id_of_shop = ?";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
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
        catch (SQLException e) {
            e.printStackTrace();
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

