package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.demo.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Profile {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button close;

    @FXML
    private Button component;

    @FXML
    private TextField curpassword;

    @FXML
    private TextField curusername;

    @FXML
    private Button line;

    @FXML
    private Button logout;

    @FXML
    private TextField newpassword;

    @FXML
    private TextField newusername;

    @FXML
    private Button order;

    @FXML
    private Button piece;

    @FXML
    private Button savepassword;

    @FXML
    private Button saveusername;

    @FXML
    private Button shop;

    @FXML
    private Button user;

    @FXML
    void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void logout(ActionEvent event) {
        HelloApplication.changeScene("/Viewer/Authorization.fxml");
    }

    @FXML
    void savepassword(ActionEvent event) {

    }

    @FXML
    void saveusername(ActionEvent event) {

    }

    @FXML
    void tocomponent(ActionEvent event) {

    }

    @FXML
    void toline(ActionEvent event) {
        HelloApplication.changeScene("/Viewer/TableLines.fxml");
    }

    @FXML
    void toorder(ActionEvent event) {
        HelloApplication.changeScene("/Viewer/TableOrder.fxml");
    }

    @FXML
    void topiece(ActionEvent event) {
        HelloApplication.changeScene("/Viewer/TablePiece.fxml");
    }

    @FXML
    void toshop(ActionEvent event) {
        HelloApplication.changeScene("/Viewer/TableShop.fxml");
    }

    @FXML
    void touser(ActionEvent event) {
        HelloApplication.changeScene("/Viewer/TableUser.fxml");
    }

    @FXML
    void initialize() {


    }

}
