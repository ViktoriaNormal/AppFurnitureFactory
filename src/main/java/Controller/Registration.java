package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.demo.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    private Button register;

    @FXML
    private TextField username;

    @FXML
    void toregister(ActionEvent event) {
        HelloApplication.changeScene("/Viewer/Authorization.fxml");
    }

    @FXML
    void initialize() {

    }

}

