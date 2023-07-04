package com.example.demo;

import java.sql.*;

public class User {

    private int id_user;
    private String user_name;
    private String password;
    private int availability_level;

    public User(int id_user, String user_name, String password, int availability_level) {
        this.id_user = id_user;
        this.user_name = user_name;
        this.password = password;
        this.availability_level = availability_level;
    }

    public User(int id_user, String user_name, String password) {
        this.id_user = id_user;
        this.user_name = user_name;
        this.password = password;
        this.availability_level = 0;
    }

    public static User insertUser(Connection connection, User user) throws SQLException {
        String query = "INSERT INTO users (id_user, user_name, password, availability_level) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, user.getId_user());
            statement.setString(2, user.getUser_name());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getAvailability_level());
            statement.executeUpdate();
        }
        return user;
    }

    public static User deleteUser(Connection connection, int id_user) throws SQLException {
        String query = "DELETE FROM users WHERE id_user = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_user);
            statement.executeUpdate();
        }
        return null;
    }

    public static User updateUser(Connection connection, User user) throws SQLException {
        String query = "UPDATE users SET user_name = ?, password = ?, availability_level = ? WHERE id_user = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUser_name());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getAvailability_level());
            statement.setInt(4, user.getId_user());
            statement.executeUpdate();
        }
        return user;
    }

    public static User[] selectAllUsers(Connection connection) throws SQLException {
        String query = "SELECT * FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            User[] users = new User[columnCount];
            int index = 0;
            while (resultSet.next()) {
                int idUser = resultSet.getInt("id_user");
                String userName = resultSet.getString("user_name");
                String password = resultSet.getString("password");
                int level = resultSet.getInt("availability_level");
                users[index++] = new User(idUser, userName, password, level);
            }
            return users;
        }
    }

    public static User selectUserById(Connection connection, int id_user) throws SQLException {
        String query = "SELECT * FROM users WHERE id_user = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_user);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idUser = resultSet.getInt("id_user");
                    String userName = resultSet.getString("user_name");
                    String password = resultSet.getString("password");
                    int level = resultSet.getInt("availability_level");
                    return new User(idUser, userName, password, level);
                }
            }
        }
        return null;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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


