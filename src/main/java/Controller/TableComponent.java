package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Service.Component;
import Service.Connector;
import Service.User;
import com.example.demo.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableComponent implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button close;

    @FXML
    private Button component;

    @FXML
    private TableColumn<Component, String> component_type;

    @FXML
    private TextField component_typefield;

    @FXML
    private TableView<Component> tableComponent;

    @FXML
    private TableColumn<Component, Integer> confidentiality_level;

    @FXML
    private TextField confidentiality_levelfield;

    @FXML
    private Button delete;

    @FXML
    private TableColumn<Component, Integer> digital_code;

    @FXML
    private TextField digital_codefield;

    @FXML
    private TableColumn<Component, Integer> id_of_component;

    @FXML
    private TextField id_of_componenttodelete;

    @FXML
    private Button insert;

    @FXML
    private Button line;

    @FXML
    private TableColumn<Component, Integer> manufacturing_price;

    @FXML
    private TextField manufacturing_pricefield;

    @FXML
    private Button order;

    @FXML
    private Button piece;

    @FXML
    private Button profile;

    @FXML
    private Button shop;

    @FXML
    private Button update;

    @FXML
    private Button user;

    @FXML
    void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void insert(ActionEvent event) {

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

    }

    @FXML
    void initialize() {


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_of_component.setCellValueFactory(new PropertyValueFactory<>("id_of_component"));
        component_type.setCellValueFactory(new PropertyValueFactory<>("component_type"));
        manufacturing_price.setCellValueFactory(new PropertyValueFactory<>("manufacturing_price"));
        digital_code.setCellValueFactory(new PropertyValueFactory<>("digital_code"));
        confidentiality_level.setCellValueFactory(new PropertyValueFactory<>("confidentiality_level"));

        try {
            tableComponent.setItems(Component.selectAllComponents());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//            String query = "SELECT * FROM users";
//            PreparedStatement statement = connection.prepareStatement(query);
//
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String login = resultSet.getString("login");
//                String password = resultSet.getString("password");
//                int role = resultSet.getInt("role");
//
//                carTable.getItems().addAll(new User(id,login,password,role));
//            }
//
//            connection.close();

//        id.setCellValueFactory(new PropertyValueFactory<>("id"));
//        login.setCellValueFactory(new PropertyValueFactory<>("login"));
//        password.setCellValueFactory(new PropertyValueFactory<>("password"));
//        role.setCellValueFactory(new PropertyValueFactory<>("role"));



}
