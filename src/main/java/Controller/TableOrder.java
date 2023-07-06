package Controller;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import Service.LineOfFurniture;
import Service.Order;
import com.example.demo.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableOrder implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Order> tableOrders;

    @FXML
    private Button close;

    @FXML
    private Button component;

    @FXML
    private TextField confidentiality_levelfield;

    @FXML
    private TextField date_of_orderfield;

    @FXML
    private Button delete;

    @FXML
    private TableColumn<Order, Integer> id_of_order;

    @FXML
    private TableColumn<Order, Integer> confidentiality_level;

    @FXML
    private TableColumn<Order, Date> date_of_order;

    @FXML
    private TableColumn<Order, Integer> id_of_shop;

    @FXML
    private TableColumn<Order, Integer> number_of_order;

    @FXML
    private TextField id_of_ordertodelete;

    @FXML
    private TextField id_of_piece;

    @FXML
    private TextField id_of_shopfield;

    @FXML
    private Button insert;

    @FXML
    private Button line;

    @FXML
    private Button makeorder;

    @FXML
    private TextField number_of_orderfield;

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
    void makeorder(ActionEvent event) {

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
        id_of_order.setCellValueFactory(new PropertyValueFactory<>("id_of_order"));
        id_of_shop.setCellValueFactory(new PropertyValueFactory<>("id_of_shop"));
        date_of_order.setCellValueFactory(new PropertyValueFactory<>("date_of_order"));
        number_of_order.setCellValueFactory(new PropertyValueFactory<>("number_of_order"));
        confidentiality_level.setCellValueFactory(new PropertyValueFactory<>("confidentiality_level"));

        tableOrders.setItems(Order.selectAllOrders());
    }

}
