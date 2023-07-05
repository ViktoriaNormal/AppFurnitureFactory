package Controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;

import Animation.Animation;
import Service.Connector;
import Service.User;
import com.example.demo.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

public class Registration {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField confirmpassword;

    @FXML
    private TextField password;

    @FXML
    private Button close;

    @FXML
    private Button register;

    @FXML
    private TextField username;

    @FXML
    void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void toregister(ActionEvent event) {
        try {

            Animation animationUsername = new Animation(username);
            Animation animationPassword = new Animation(password);
            Animation shapeEmail = new Animation(password);

            String passwordInsert = password.getText();
            String usernameInsert = username.getText();

            String userName = username.getText();
            String hashPassword = User.makeMD5(password.getText());
            int availability_level = 0;

            if (userName.isEmpty() || hashPassword.isEmpty()) {
                animationUsername.playAnimation();
                animationPassword.playAnimation();
            }else {
                String query = "INSERT INTO users (username, password, availability_level) VALUES (?, ?, ?);";
                PreparedStatement statement = Connector.getConnection().prepareStatement(query);

                statement.setString(1, userName);
                statement.setString(2, hashPassword);
                statement.setInt(3, availability_level);

                try {
                    statement.execute();
                    HelloApplication.changeScene("/Viewer/Authorization.fxml");
                } catch (SQLIntegrityConstraintViolationException e) {
                    animationUsername.playAnimation();
                    animationPassword.playAnimation();
                }
            }
            Connector.breakConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }

}

