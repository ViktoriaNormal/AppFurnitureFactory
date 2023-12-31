package Service;

import java.sql.*;

public class Connector {
    private static Connection _database_connection;

    public Connector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            _database_connection = DriverManager.getConnection("jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2293_furniture_factory",
                    "std_2293_furniture_factory", "mechta1357");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Драйвер JDBC не найден");
        }
        catch (SQLException e) {
            System.out.println("При попытке соединения с базой данных произошла ошибка: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return _database_connection;
    }

    public static void breakConnection() {
        try {
            if (_database_connection != null && !_database_connection.isClosed())
                _database_connection.close();
        }
        catch (SQLException e) {
            System.out.println("При попытке разорвать соединение с базой данных произошла ошибка: " + e.getMessage());
        }
    }
}


