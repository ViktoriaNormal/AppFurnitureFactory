package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import Animation.Animation;
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
    private TextField idupdate;

    @FXML
    private Button user;

    @FXML
    void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void delete(ActionEvent event) {
        Animation animationPassword = new Animation(id_of_componenttodelete);

        String idDel = id_of_componenttodelete.getText();
        try {
            Component component = Component.selectComponentById(Integer.parseInt(idDel));
            if (component != null) {
                component.deleteComponent();

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
        Animation animation1 = new Animation(component_typefield);
        Animation animation2 = new Animation(manufacturing_pricefield);
        Animation animation3 = new Animation(digital_codefield);

        String component_typeField = component_typefield.getText();
        String manufacturing_PriceField = manufacturing_pricefield.getText();
        String digital_codeField = digital_codefield.getText();

        Component component1;

        try {
            if (!component_typeField.equals("") && !manufacturing_PriceField.equals("") && !digital_codeField.equals("")) {
                component1 = new Component(component_typeField, Integer.parseInt(manufacturing_PriceField), Integer.parseInt(digital_codeField));
                component1.insertComponent();
                changeViewer();
            } else {
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
        Animation animation1 = new Animation(component_typefield);
        Animation animation2 = new Animation(manufacturing_pricefield);
        Animation animation3 = new Animation(digital_codefield);
        Animation animation4 = new Animation(idupdate);

        String component_typeField = component_typefield.getText();
        String manufacturing_priceField = manufacturing_pricefield.getText();
        String digital_codeField = digital_codefield.getText();
        String confidentiality_levelField = confidentiality_levelfield.getText();
        String idUpdate = idupdate.getText();

        try {
            if(!idUpdate.equals("")) {
                if (!component_typeField.equals("")) {
                    Objects.requireNonNull(Component.selectComponentById(Integer.parseInt(idUpdate))).updateComponentST("component_type", component_typeField);
                }
                if (!manufacturing_priceField.equals("")) {
                    Objects.requireNonNull(Component.selectComponentById(Integer.parseInt(idUpdate))).updateComponentDig("manufacturing_price", manufacturing_priceField);
                }
                if (!digital_codeField.equals("")) {
                    Objects.requireNonNull(Component.selectComponentById(Integer.parseInt(idUpdate))).updateComponentDig("digital_code", digital_codeField);
                }
                if (!confidentiality_levelField.equals("")) {
                    Objects.requireNonNull(Component.selectComponentById(Integer.parseInt(idUpdate))).updateComponentDig("confidentiality_level", confidentiality_levelField);
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



}
