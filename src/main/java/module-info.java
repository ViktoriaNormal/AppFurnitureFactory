module com.example.demo {
        requires javafx.controls;
        requires javafx.fxml;
        requires java.sql;
        requires mysql.connector.java;

        exports Service;
        exports com.example.demo;
        exports Controller;

        opens com.example.demo to javafx.fxml;
        opens Service to javafx;
        opens Controller to javafx.fxml;
}
