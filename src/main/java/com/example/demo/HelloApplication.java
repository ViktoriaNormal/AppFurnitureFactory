package com.example.demo;


import Service.Component;
import Service.Connector;
import Service.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class HelloApplication extends Application {
    private static Stage stg;
    @Override
    public void start(Stage primaryStage) throws IOException {
        stg = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("/Viewer/Authorization.fxml")));
        primaryStage.setTitle("Furniture factory");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }

    public static Object changeScene(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxml));
            Parent pane = loader.load();
            stg.setScene(new Scene(pane));
            return loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {

        Connector connector = new Connector();
//        System.out.println(Component.selectAllComponents());
//        User user = new User("login", "login");
//        user.insertUser();
//        System.out.println(user.updateUser());
        launch();
    }
}