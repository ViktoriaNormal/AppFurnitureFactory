package Controller;

import java.net.URL;
import java.util.ResourceBundle;

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

    public void submitChange() throws NoSuchAlgorithmException, InvalidKeySpecException, SQLException {
        String password = passwordText.getText();    String change = changeText.getText();    passwordText.setText("");    changeText.setText("");    if (!change.matches("[A-Za-z0-9]+")) {
            errorLabel.setText("Только латинские букв или цифры");        return;    }
        if (!userService.passwordHashing(password).equals(user.getPassword())) {
            errorLabel.setText("Неправильный пароль");        return;    }
        if (passwordChange) {
            userService.changePassword(user, change);        user.setPassword(userService.passwordHashing(change));    } else {
            if (userService.exists(change)) {
                errorLabel.setText("Данный логин занят");            return;        }
            userService.changeName(user, change);        user.setUsername(change);    }
        errorLabel.setText("");    changeSuccessLabel.setVisible(true);    showInfo();}
    @FXML
    void savepassword(ActionEvent event) {

    }

    @FXML
    void saveusername(ActionEvent event) {
        String currentUsername = curusername.getText();
        String newUsername = newusername.getText();
        if (!currentUsername.equals(User.getInstance().getUsername())) {
            User.getInstance().updateUser("username", newUsername);
        }
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
