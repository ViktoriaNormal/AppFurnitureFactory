package Controller;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Animation.Animation;
import Service.Connector;
import Service.User;
import com.example.demo.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class Authorization {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button close;

    @FXML
    private PasswordField password;

    @FXML
    private Button signin;

    @FXML
    private Button signup;

    @FXML
    private TextField username;
    @FXML
    private Label error;

    @FXML
    void close(ActionEvent event) {
        System.exit(0);
    }

//    @FXML
//    void signin(ActionEvent event) {
//        try {
//            Animation animationPassword = new Animation(password);
//            Animation animationUsername = new Animation(username);
//
//            String userName = username.getText();
//            String hashPassword = User.makeMD5(password.getText());
//            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
//            PreparedStatement statement = Connector.getConnection().prepareStatement(query);
//
//            statement.setString(1, userName);
//            statement.setString(2, hashPassword);
//            ResultSet result = statement.executeQuery();
//            if (result.next()) {
//                HelloApplication.changeScene("/Viewer/TableLines.fxml");
//            }
//            else {
//                animationPassword.playAnimation();
//                animationUsername.playAnimation();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @FXML
    void signin(ActionEvent event) {
        try {
            Animation animationPassword = new Animation(password);
            Animation animationUsername = new Animation(username);
            String userName = username.getText();
            String hashPassword = password.getText();
            if (User.selectUserByUNP(userName, User.makeMD5(hashPassword)) != null) {
                User.login(new User(userName, User.makeMD5(hashPassword)));
                HelloApplication.changeScene("/Viewer/TableLines.fxml");
            }
            else {
                animationPassword.playAnimation();
                animationUsername.playAnimation();        }
        } catch (Exception e) {
            e.printStackTrace();    }
    }

    @FXML
    void signup(ActionEvent event) {
        HelloApplication.changeScene("/Viewer/Registration.fxml");
    }

    @FXML
    void initialize() {

    }

}
