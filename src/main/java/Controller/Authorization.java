package Controller;


import java.net.URL;
import java.util.ResourceBundle;

import com.example.demo.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void signin(ActionEvent event) {
        HelloApplication.changeScene("/Viewer/TableLines.fxml");
    }

    @FXML
    void signup(ActionEvent event) {
        HelloApplication.changeScene("/Viewer/Registration.fxml");
    }

    @FXML
    void initialize() {

    }

}
