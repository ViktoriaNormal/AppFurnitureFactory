package Controller;

import java.net.URL;
import java.sql.Date;
import java.util.Objects;
import java.util.ResourceBundle;

import Animation.Animation;
import Service.Component;
import Service.LineOfFurniture;
import Service.Order;
import Service.Shop;
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
    private TextField idUpdate;

    @FXML
    private Button user;

    @FXML
    void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void delete(ActionEvent event) {
        Animation animationPassword = new Animation(id_of_ordertodelete);

        String idDel = id_of_ordertodelete.getText();
        try {
            Order order1 = Order.selectOrderById(Integer.parseInt(idDel));
            if (order1 != null) {
                order1.deleteOrder();

                changeViewer();
            } else {
                animationPassword.playAnimation();
            }
        }
        catch (Exception e) {
            animationPassword.playAnimation();
        }
    }

    @FXML
    void insert(ActionEvent event) {
        Animation animation1 = new Animation(id_of_shopfield);
        Animation animation2 = new Animation(date_of_orderfield);
        Animation animation3 = new Animation(number_of_orderfield);

        String id_of_shopField = id_of_shopfield.getText();
        String date_of_orderField = date_of_orderfield.getText();
        String number_of_orderField = number_of_orderfield.getText();

        Order order1;

        try {
            if(!id_of_shopField.equals("") && !date_of_orderField.equals("") && !number_of_orderField.equals("")) {
                order1 = new Order(Objects.requireNonNull(Shop.selectShopById(Integer.parseInt(id_of_shopField))),
                        Date.valueOf(date_of_orderField),
                        Integer.parseInt(number_of_orderField));
                order1.insertOrder();
                changeViewer();
            }
            else {
                animation1.playAnimation();
                animation2.playAnimation();
                animation3.playAnimation();
            }
        }
        catch (Exception e) {
            animation1.playAnimation();
            animation2.playAnimation();
            animation3.playAnimation();
        }
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
        Animation animation1 = new Animation(id_of_shopfield);
        Animation animation2 = new Animation(date_of_orderfield);
        Animation animation3 = new Animation(number_of_orderfield);
        Animation animation4 = new Animation(confidentiality_levelfield);

        String id_of_shopField = id_of_shopfield.getText();
        String date_of_orderField = date_of_orderfield.getText();
        String number_of_orderField = number_of_orderfield.getText();
        String confidentiality_levelField = confidentiality_levelfield.getText();
        String idupdate = idUpdate.getText();

        try {
            if(!idupdate.equals("")) {
                if (!id_of_shopField.equals("")) {
                    Objects.requireNonNull(Order.selectOrderById(Integer.parseInt(idupdate))).updateOrder("id_of_shop", id_of_shopField);
                }
                if (!date_of_orderField.equals("")) {
                    Objects.requireNonNull(Order.selectOrderById(Integer.parseInt(idupdate))).updateOrderST("date_of_order", date_of_orderField);
                }
                if (!number_of_orderField.equals("")) {
                    Objects.requireNonNull(Order.selectOrderById(Integer.parseInt(idupdate))).updateOrder("number_of_order", number_of_orderField);
                }
                if (!confidentiality_levelField.equals("")) {
                    Objects.requireNonNull(Order.selectOrderById(Integer.parseInt(idupdate))).updateOrder("confidentiality_level", confidentiality_levelField);
                }
                changeViewer();
            }
            else {
                animation1.playAnimation();
                animation2.playAnimation();
                animation3.playAnimation();
                animation4.playAnimation();
            }
        }
        catch (Exception e) {
            animation1.playAnimation();
            animation2.playAnimation();
            animation3.playAnimation();
            animation4.playAnimation();
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
        id_of_order.setCellValueFactory(new PropertyValueFactory<>("id_of_order"));
        id_of_shop.setCellValueFactory(new PropertyValueFactory<>("id_of_shop"));
        date_of_order.setCellValueFactory(new PropertyValueFactory<>("date_of_order"));
        number_of_order.setCellValueFactory(new PropertyValueFactory<>("number_of_order"));
        confidentiality_level.setCellValueFactory(new PropertyValueFactory<>("confidentiality_level"));

        tableOrders.setItems(Order.selectAllOrders());
    }

}
