package Service;

import java.sql.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private int id_of_order;
    private int id_of_shop;
    private Date date_of_order;
    private int number_of_order;
    private int confidentiality_level;

    public Order(int id_of_order, Shop shop, Date date_of_order, int number_of_order, int confidentiality_level) {
        this.id_of_order = id_of_order;
        this.id_of_shop = shop.getId_of_shop();
        this.date_of_order = date_of_order;
        this.number_of_order = number_of_order;
        this.confidentiality_level = confidentiality_level;
    }

    public Order(int id_of_order, Shop shop, Date date_of_order, int number_of_order) {
        this.id_of_order = id_of_order;
        this.id_of_shop = shop.getId_of_shop();
        this.date_of_order = date_of_order;
        this.number_of_order = number_of_order;
        this.confidentiality_level = 0;
    }

    public Order insertOrder() {
        String query = "INSERT INTO orders (id_of_shop, date_of_order, number_of_order) VALUES (?, ?, ?)";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setInt(2, getId_of_shop());
            statement.setDate(3, getDate_of_order());
            statement.setInt(4, getNumber_of_order());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Order deleteOrder() {
        String query = "DELETE FROM orders WHERE id_of_order = ?";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setInt(1, getId_of_order());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Order updateOrder(String column_name, Object new_value) {
        String query = "UPDATE orders SET " + column_name + " = " + new_value + " WHERE id_of_order = " + getId_of_order();
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            switch (column_name) {
                case "id_of_shop" -> setId_of_shop(Objects.requireNonNull(Shop.selectShopById((int) new_value)));
                case "date_of_order" -> setDate_of_order((Date) new_value);
                case "number_of_order" -> setNumber_of_order((int) new_value);
            }
        }
        catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }

        return this;
    }

//    public static Order[] selectAllOrders() {
//        String query = "SELECT * FROM orders";
//        try {
//            PreparedStatement statement = Connector.getConnection().prepareStatement(query);
//            ResultSet resultSet = statement.executeQuery(query);
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            int columnCount = metaData.getColumnCount();
//            Order[] orders = new Order[columnCount];
//            int index = 0;
//            while (resultSet.next()) {
//                int idOrder = resultSet.getInt("id_of_order");
//                int idShop = resultSet.getInt("id_of_shop");
//                Date date = resultSet.getDate("date_of_order");
//                int number = resultSet.getInt("number_of_order");
//                int level = resultSet.getInt("confidentiality_level");
//                orders[index++] = new Order(idOrder, Objects.requireNonNull(Shop.selectShopById(idShop)), date, number,
//                        level);
//            }
//            return orders;
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static List<Order> selectAllOrders() {
        String query = "SELECT * FROM orders";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                int idOrder = resultSet.getInt("id_of_order");
                int idShop = resultSet.getInt("id_of_shop");
                Date date = resultSet.getDate("date_of_order");
                int number = resultSet.getInt("number_of_order");
                int level = resultSet.getInt("confidentiality_level");
                orders.add(new Order(idOrder, Objects.requireNonNull(Shop.selectShopById(idShop)), date, number, level));
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Order selectOrderById(int id_of_order) {
        String query = "SELECT * FROM orders WHERE id_of_order = ?";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setInt(1, id_of_order);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idOrder = resultSet.getInt("id_of_order");
                    int idShop = resultSet.getInt("id_of_shop");
                    Date date = resultSet.getDate("date_of_order");
                    int number = resultSet.getInt("number_of_order");
                    int level = resultSet.getInt("confidentiality_level");
                    return new Order(idOrder, Objects.requireNonNull(Shop.selectShopById(idShop)), date, number, level);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getId_of_order() {
        return id_of_order;
    }

    public void setId_of_order(int id_of_order) {
        this.id_of_order = id_of_order;
    }

    public int getId_of_shop() {
        return id_of_shop;
    }

    public void setId_of_shop(Shop shop) {
        this.id_of_shop = shop.getId_of_shop();
    }

    public Date getDate_of_order() {
        return date_of_order;
    }

    public void setDate_of_order(Date date_of_order) {
        this.date_of_order = date_of_order;
    }

    public int getNumber_of_order() {
        return number_of_order;
    }

    public void setNumber_of_order(int number_of_order) {
        this.number_of_order = number_of_order;
    }

    public int getConfidentiality_level() {
        return confidentiality_level;
    }

    public void setConfidentiality_level(int confidentiality_level) {
        this.confidentiality_level = confidentiality_level;
    }
}

