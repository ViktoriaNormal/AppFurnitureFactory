package Controller;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import Animation.Animation;
import Service.Component;
import Service.Shop;
import Service.User;
import com.example.demo.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableUser implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField availability_levelfield;

    @FXML
    private Button close;

    @FXML
    private Button component;

    @FXML
    private Button delete;

    @FXML
    private TableColumn<User, Integer> id_user;

    @FXML
    private TableColumn<User, Integer> availability_level;

    @FXML
    private TableColumn<User, String> password;

    @FXML
    private TableView<User> tableUsers;

    @FXML
    private TableColumn<User, String> username;

    @FXML
    private TextField id_usertodelete;

    @FXML
    private Button insert;

    @FXML
    private Button line;

    @FXML
    private Button order;

    @FXML
    private TextField passwordfield;

    @FXML
    private Button piece;

    @FXML
    private Button profile;

    @FXML
    private TextField idupdate;

    @FXML
    private Button shop;

    @FXML
    private Button update;

    @FXML
    private Button user;

    @FXML
    private TextField usernamefield;

    @FXML
    void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void delete(ActionEvent event) {
        Animation animationPassword = new Animation(id_usertodelete);

        String idDel = id_usertodelete.getText();
        User user1 = User.selectUserById(Integer.parseInt(idDel));
        if(user1 != null) {
            user1.deleteUser();

            changeViewer();
        }
        else {
            animationPassword.playAnimation();
        }
    }

    @FXML
    void insert(ActionEvent event) {
        Animation animation1 = new Animation(usernamefield);
        Animation animation2 = new Animation(passwordfield);

        String usernameField = usernamefield.getText();
        String passwordField = passwordfield.getText();

        User user1;

        try {
            if(!usernameField.equals("") && !passwordField.equals("")) {
                user1 = new User(usernameField, User.makeMD5(passwordField));
                user1.insertUser();
                changeViewer();
            }
            else {
                animation1.playAnimation();
                animation2.playAnimation();
            }
        }
        catch (Exception e) {
            animation1.playAnimation();
            animation2.playAnimation();
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
    void toprofile(ActionEvent event) {
        HelloApplication.changeScene("/Viewer/Profile.fxml");
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
    void update(ActionEvent event) {
        Animation animation1 = new Animation(usernamefield);
        Animation animation2 = new Animation(passwordfield);
        Animation animation5 = new Animation(availability_levelfield);
        Animation animation6 = new Animation(idupdate);

        String usernameField = usernamefield.getText();
        String passwordField = passwordfield.getText();
        String availability_levelField = availability_levelfield.getText();
        String idUpdate = idupdate.getText();

        try {
            if(!idUpdate.equals("")) {
                if (!usernameField.equals("")) {
                    Objects.requireNonNull(User.selectUserById(Integer.parseInt(idUpdate))).updateName(usernameField);
                }
                if (!passwordField.equals("")) {
                    Objects.requireNonNull(User.selectUserById(Integer.parseInt(idUpdate))).updatePassword(passwordField);
                }
                if (!availability_levelField.equals("")) {
                    Objects.requireNonNull(User.selectUserById(Integer.parseInt(idUpdate))).updateUser("availability_level", availability_levelField);
                }
                changeViewer();
            }
            else {
                animation1.playAnimation();
                animation2.playAnimation();
                animation5.playAnimation();
                animation6.playAnimation();
            }
        }
        catch (Exception e) {
            animation1.playAnimation();
            animation2.playAnimation();
            animation5.playAnimation();
            animation6.playAnimation();
        }
    }

    @FXML
    void initialize() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        changeViewer();
    }

    public void changeViewer() {
        id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        availability_level.setCellValueFactory(new PropertyValueFactory<>("availability_level"));

        tableUsers.setItems(User.selectAllUsers());
    }

}
