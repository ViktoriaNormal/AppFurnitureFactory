package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Animation.Animation;
import Service.User;
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
        Animation animationCurPassword = new Animation(curpassword);
        Animation animationNewPassword = new Animation(newpassword);

        String currentPassword = User.makeMD5(curpassword.getText());
        System.out.println(currentPassword);
        System.out.println(User.getInstance().getPassword());
        String newPassword = User.makeMD5(newpassword.getText());
        if (currentPassword.equals(User.getInstance().getPassword())) {
            User.getInstance().updateUser("password", newPassword);
        }
        else {
            animationCurPassword.playAnimation();
            animationNewPassword.playAnimation();
        }
    }

    @FXML
    void saveusername(ActionEvent event) {
        Animation animationCurUsername = new Animation(curusername);
        Animation animationNewUsername = new Animation(newusername);

        String currentUsername = curusername.getText();
        System.out.println(currentUsername);
        System.out.println(User.getInstance().getUsername());
        String newUsername = newusername.getText();
        if (currentUsername.equals(User.getInstance().getUsername())) {
            User.getInstance().updateUser("username", newUsername);
        }
        else {
            animationCurUsername.playAnimation();
            animationNewUsername.playAnimation();
        }
    }

    @FXML
    void tocomponent(ActionEvent event) {
        HelloApplication.changeScene("/Viewer/TableComponent.fxml");
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
