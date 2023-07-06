package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import Animation.Animation;
import Service.Component;
import Service.LineOfFurniture;
import com.example.demo.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableLines implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button close;

    @FXML
    private Button component;

    @FXML
    private TableView<LineOfFurniture> tableLines;

    @FXML
    private TableColumn<LineOfFurniture, Integer> confidentiality_level;

    @FXML
    private TextField confidentiality_levelfield;

    @FXML
    private Button delete;

    @FXML
    private TableColumn<LineOfFurniture, Integer> id_line;

    @FXML
    private TextField idtodelete;

    @FXML
    private Button insert;

    @FXML
    private Button line;

    @FXML
    private TableColumn<LineOfFurniture, String> name;

    @FXML
    private TextField namefield;

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
        Animation animationPassword = new Animation(idtodelete);

        String idDel = idtodelete.getText();
        try {
            LineOfFurniture lineOfFurniture = LineOfFurniture.selectLineOfFurnitureById(Integer.parseInt(idDel));
            if (lineOfFurniture != null) {
                lineOfFurniture.deleteLineOfFurniture();

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
        Animation animation1 = new Animation(namefield);

        String nameField = namefield.getText();

        LineOfFurniture lineOfFurniture;

        if(!nameField.equals("")) {
            lineOfFurniture = new LineOfFurniture(nameField);
            lineOfFurniture.insertLineOfFurniture();
            changeViewer();
        }
        else {
            animation1.playAnimation();
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
        Animation animation1 = new Animation(namefield);
        Animation animation2 = new Animation(confidentiality_levelfield);
        String confidentiality_levelField = confidentiality_levelfield.getText();
        String nameField = namefield.getText();
        Animation animation4 = new Animation(idupdate);
        String idUpdate = idupdate.getText();

        try {
            if(!idUpdate.equals("")) {
                if (!nameField.equals("")) {
                    Objects.requireNonNull(LineOfFurniture.selectLineOfFurnitureById(Integer.parseInt(idUpdate))).updateLineName(nameField);
                }
                changeViewer();
            }
            else {
                animation1.playAnimation();
                animation2.playAnimation();
                animation4.playAnimation();
            }
        }
        catch (Exception e) {
            animation1.playAnimation();
            animation2.playAnimation();
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
        id_line.setCellValueFactory(new PropertyValueFactory<>("id_line"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        confidentiality_level.setCellValueFactory(new PropertyValueFactory<>("confidentiality_level"));

        tableLines.setItems(LineOfFurniture.selectAllLinesOfFurniture());
    }

}
