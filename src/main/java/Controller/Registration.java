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

            String userName = username.getText();
            String hashPassword = password.getText();

            if (userName.isEmpty() || hashPassword.isEmpty()) {
                animationUsername.playAnimation();
                animationPassword.playAnimation();
            }else {
                User user = new User(userName, User.makeMD5(hashPassword));
                try{
                user.insertUser();
                HelloApplication.changeScene("/Viewer/Authorization.fxml");
                } catch (Exception e) {
                    animationUsername.playAnimation();
                    animationPassword.playAnimation();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }

}

