package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Service.Order;
import Service.PieceOfFurniture;
import com.example.demo.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TablePiece implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addcomponent;

    @FXML
    private TextField article_numberfield;

    @FXML
    private Button close;

    @FXML
    private Button component;

    @FXML
    private TextField confidentiality_levelfield;

    @FXML
    private Button delete;

    @FXML
    private TableColumn<PieceOfFurniture, Integer> article_number;

    @FXML
    private TableColumn<PieceOfFurniture, Integer> confidentiality_level;

    @FXML
    private TableColumn<PieceOfFurniture, Integer> id_line;

    @FXML
    private TableColumn<PieceOfFurniture, Integer> price;

    @FXML
    private TableView<PieceOfFurniture> tablePieces;

    @FXML
    private TextField id_linefield;

    @FXML
    private TableColumn<?, ?> id_of_piece;

    @FXML
    private TextField id_of_piecetodelete;

    @FXML
    private Button insert;

    @FXML
    private Button line;

    @FXML
    private Button order;

    @FXML
    private Button piece;

    @FXML
    private TableColumn<?, ?> piece_type;

    @FXML
    private TextField piece_typefield;

    @FXML
    private TextField pricefield;

    @FXML
    private TextField id_of_component;

    @FXML
    private Button profile;

    @FXML
    private Button shop;

    @FXML
    private Button update;

    @FXML
    private Button user;

    @FXML
    void addcomponent(ActionEvent event) {

    }

    @FXML
    void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void id_of_component(ActionEvent event) {

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
        id_of_piece.setCellValueFactory(new PropertyValueFactory<>("id_of_piece"));
        piece_type.setCellValueFactory(new PropertyValueFactory<>("piece_type"));
        article_number.setCellValueFactory(new PropertyValueFactory<>("article_number"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        id_line.setCellValueFactory(new PropertyValueFactory<>("id_line"));
        confidentiality_level.setCellValueFactory(new PropertyValueFactory<>("confidentiality_level"));

        tablePieces.setItems(PieceOfFurniture.selectAllPiecesOfFurniture());
    }

}
