package Controller;

import java.net.URL;
import java.sql.Date;
import java.util.Objects;
import java.util.ResourceBundle;

import Animation.Animation;
import Service.Component;
import Service.Order;
import Service.PieceOfFurniture;
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

public class TableShop implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField idupdate;

    @FXML
    private TextField addressfield;

    @FXML
    private TableColumn<Shop, String> address;

    @FXML
    private TableColumn<Shop, Integer> confidentiality_level;

    @FXML
    private TableColumn<Shop, Integer> fax_number;

    @FXML
    private TableView<Shop> tableShops;

    @FXML
    private Button close;

    @FXML
    private Button component;

    @FXML
    private TextField confidentiality_levelfield;

    @FXML
    private Button delete;

    @FXML
    private TextField fax_numberfield;

    @FXML
    private TableColumn<?, ?> id_of_shop;

    @FXML
    private TextField id_of_shoptodelete;

    @FXML
    private Button insert;

    @FXML
    private Button line;

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
        Animation animationPassword = new Animation(id_of_shoptodelete);

        String idDel = id_of_shoptodelete.getText();
        Shop shop1 = Shop.selectShopById(Integer.parseInt(idDel));
        if(shop1 != null) {
            shop1.deleteShop();


        }
        else {
            animationPassword.playAnimation();
        }
    }

    @FXML
    void insert(ActionEvent event) {
        Animation animation1 = new Animation(addressfield);
        Animation animation2 = new Animation(fax_numberfield);

        String addressField = addressfield.getText();
        String fax_numberField = fax_numberfield.getText();

        Shop shop1;

        try {
            if(!addressField.equals("") && !fax_numberField.equals("")) {
                shop1 = new Shop(addressField, Integer.parseInt(fax_numberField));
                shop1.insertShop();
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
        Animation animation1 = new Animation(addressfield);
        Animation animation2 = new Animation(fax_numberfield);
        Animation animation5 = new Animation(confidentiality_levelfield);
        Animation animation6 = new Animation(idupdate);

        String addressField = addressfield.getText();
        String fax_numberField = fax_numberfield.getText();
        String confidentiality_levelField = confidentiality_levelfield.getText();
        String idUpdate = idupdate.getText();

        try {
            if(!idUpdate.equals("")) {
                if (!addressField.equals("")) {
                    Objects.requireNonNull(Shop.selectShopById(Integer.parseInt(idUpdate))).updateShopST("address", addressField);
                }
                if (!fax_numberField.equals("")) {
                    Objects.requireNonNull(Shop.selectShopById(Integer.parseInt(idUpdate))).updateShopDig("fax_number", fax_numberField);
                }
                if (!confidentiality_levelField.equals("")) {
                    Objects.requireNonNull(Shop.selectShopById(Integer.parseInt(idUpdate))).updateShopDig("confidentiality_level", confidentiality_levelField);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        changeViewer();
    }

    public void changeViewer() {
        id_of_shop.setCellValueFactory(new PropertyValueFactory<>("id_of_shop"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        fax_number.setCellValueFactory(new PropertyValueFactory<>("fax_number"));
        confidentiality_level.setCellValueFactory(new PropertyValueFactory<>("confidentiality_level"));

        tableShops.setItems(Shop.selectAllShops());
    }

}
