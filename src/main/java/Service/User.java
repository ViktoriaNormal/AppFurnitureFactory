package Service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {

    private static User object;
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

    public User(String user_name, String password) {
        this.username = user_name;
        this.password = password;
        this.availability_level = 0;
    }

    public static void login(User user) {
        object = user;
    }

    public static User getInstance() {
        return object;
    }

    public User insertUser() {
        String query = "INSERT INTO users (username, password, availability_level) VALUES (?, ?, ?)";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setString(1, getUsername());
            statement.setString(2, getPassword());
            statement.setInt(3, getAvailability_level());
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

    public User updateUser(String column_name, String new_value) {
        String query = "UPDATE users SET " + column_name + " = '" + new_value + "' WHERE id_user = " + getId_user();
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            switch (column_name) {
                case "username" -> setUsername(new_value);
                case "password" -> setPassword(new_value);
            }
        }
        catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }

        return this;
    }

    public void updateName(String newName) throws SQLException {
        String username = getUsername();
        String password = getPassword();
        Statement statement = Connector.getConnection().createStatement();
        String query = "UPDATE users SET username = '" + newName + "' WHERE username = '" + username + "' AND password = '" + password + "';";
        statement.executeUpdate(query);
    }
    public void updatePassword(String newPassword) throws SQLException {
        String username = getUsername();
        String password = getPassword();
        newPassword = makeMD5(newPassword);
        Statement statement = Connector.getConnection().createStatement();
        String query = "UPDATE users SET password = '" + newPassword + "' WHERE username = '" + username + "' AND password = '" + password + "';";
        statement.executeUpdate(query);
    }

    public static ObservableList<User> selectAllUsers() {
        String query = "SELECT * FROM users";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            ObservableList<User> users = FXCollections.observableArrayList();
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

    public static User userLogin(String username, String password) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        if(!username.matches("[A-Za-z0-9]+") || !password.matches("[A-Za-z0-9]+")) return null;
        Statement statement = Connector.getConnection().createStatement();
        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + makeMD5(password) + "';";
        ResultSet result = statement.executeQuery(query);
        if(!result.next()) return null;
        return new User(result.getInt("id_user"), result.getString("username"), result.getString("password"),
                result.getInt("availability_level"));
    }

    public static User selectUserByUNP(String userName, String Password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement statement = Connector.getConnection().prepareStatement(query)) {
            statement.setString(1, userName);
            statement.setString(2, Password);
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

    public static String makeMD5(String password) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            String pwd = new BigInteger(1, messageDigest.digest()).toString(16);
            return pwd.toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
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


