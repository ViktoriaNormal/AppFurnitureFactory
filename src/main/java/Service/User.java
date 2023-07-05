package Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {

    private int id_user;
    private String username;
    private String password;
    private int availability_level;

    public User(int id_user, String user_name, String password, int availability_level) {
        this.id_user = id_user;
        this.username = user_name;
        this.password = password;
        this.availability_level = availability_level;
    }

    public User(int id_user, String user_name, String password) {
        this.id_user = id_user;
        this.username = user_name;
        this.password = password;
        this.availability_level = 0;
    }

    public User insertUser() {
        String query = "INSERT INTO users (username, password, availability_level) VALUES (?, ?, ?)";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setString(2, getUsername());
            statement.setString(3, getPassword());
            statement.setInt(4, getAvailability_level());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public User deleteUser() {
        String query = "DELETE FROM users WHERE id_user = ?";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setInt(1, getId_user());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User updateUser(String column_name, Object new_value) {
        String query = "UPDATE users SET " + column_name + " = " + new_value + " WHERE id_user = " + getId_user();
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            switch (column_name) {
                case "username" -> setUsername((String) new_value);
                case "password" -> setPassword((String) new_value);
                case "availability_level" -> setAvailability_level((int) new_value);
            }
        }
        catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }

        return this;
    }

//    public static User[] selectAllUsers() {
//        String query = "SELECT * FROM users";
//        try {
//            PreparedStatement statement = Connector.getConnection().prepareStatement(query);
//            ResultSet resultSet = statement.executeQuery(query);
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            int columnCount = metaData.getColumnCount();
//            User[] users = new User[columnCount];
//            int index = 0;
//            while (resultSet.next()) {
//                int idUser = resultSet.getInt("id_user");
//                String username = resultSet.getString("username");
//                String password = resultSet.getString("password");
//                int level = resultSet.getInt("availability_level");
//                users[index++] = new User(idUser, username, password, level);
//            }
//            return users;
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static List<User> selectAllUsers() {
        String query = "SELECT * FROM users";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                int idUser = resultSet.getInt("id_user");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int level = resultSet.getInt("availability_level");
                users.add(new User(idUser, username, password, level));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User selectUserById(int id_user) {
        String query = "SELECT * FROM users WHERE id_user = ?";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setInt(1, id_user);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idUser = resultSet.getInt("id_user");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    int level = resultSet.getInt("availability_level");
                    return new User(idUser, username, password, level);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //метод отвечающий за доступ пользователя к таблицам

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user_name) {
        this.username = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAvailability_level() {
        return availability_level;
    }

    public void setAvailability_level(int availability_level) {
        this.availability_level = availability_level;
    }
}


