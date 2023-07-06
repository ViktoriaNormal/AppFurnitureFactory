package Controller;

import java.net.URL;
import java.sql.Date;
import java.util.Objects;
import java.util.ResourceBundle;

import Animation.Animation;
import Service.*;
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
    private TextField idupdate;

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
        Animation animationPassword = new Animation(id_of_piecetodelete);

        String idDel = id_of_piecetodelete.getText();
        PieceOfFurniture pieceOfFurniture = PieceOfFurniture.selectPieceOfFurnitureById(Integer.parseInt(idDel));
        if(pieceOfFurniture != null) {
            pieceOfFurniture.deletePieceOfFurniture();

            changeViewer();
        }
        else {
            animationPassword.playAnimation();
        }
    }

    @FXML
    void id_of_component(ActionEvent event) {

    }

    @FXML
    void insert(ActionEvent event) {
        Animation animation1 = new Animation(piece_typefield);
        Animation animation2 = new Animation(article_numberfield);
        Animation animation3 = new Animation(pricefield);
        Animation animation4 = new Animation(id_linefield);

        String piece_typeField = piece_typefield.getText();
        String article_numberField = article_numberfield.getText();
        String priceField = pricefield.getText();
        String id_lineField = id_linefield.getText();

        PieceOfFurniture pieceOfFurniture;

        try {
            if(!piece_typeField.equals("") && !article_numberField.equals("") && !priceField.equals("") && !id_lineField.equals("")) {
                pieceOfFurniture = new PieceOfFurniture(piece_typeField, Integer.parseInt(article_numberField),
                        Integer.parseInt(priceField),
                        Objects.requireNonNull(LineOfFurniture.selectLineOfFurnitureById(Integer.parseInt(id_lineField))));
                pieceOfFurniture.insertPieceOfFurniture();
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
        Animation animation1 = new Animation(piece_typefield);
        Animation animation2 = new Animation(article_numberfield);
        Animation animation3 = new Animation(pricefield);
        Animation animation4 = new Animation(id_linefield);
        Animation animation5 = new Animation(confidentiality_levelfield);
        Animation animation6 = new Animation(idupdate);

        String piece_typeField = piece_typefield.getText();
        String article_numberField = article_numberfield.getText();
        String priceField = pricefield.getText();
        String id_lineField = id_linefield.getText();
        String confidentiality_levelField = confidentiality_levelfield.getText();
        String idUpdate = idupdate.getText();

        try {
            if(!idUpdate.equals("")) {
                if (!piece_typeField.equals("")) {
                    Objects.requireNonNull(Objects.requireNonNull(PieceOfFurniture.selectPieceOfFurnitureById((Integer.parseInt(idUpdate)))).updatePieceOfFurnitureST("piece_type", piece_typeField));
                }
                if (!article_numberField.equals("")) {
                    Objects.requireNonNull(PieceOfFurniture.selectPieceOfFurnitureById(Integer.parseInt(idUpdate))).updatePieceOfFurniture("article_number", article_numberField);
                }
                if (!priceField.equals("")) {
                    Objects.requireNonNull(PieceOfFurniture.selectPieceOfFurnitureById(Integer.parseInt(idUpdate))).updatePieceOfFurniture("price", priceField);
                }
                if (!id_lineField.equals("")) {
                    Objects.requireNonNull(PieceOfFurniture.selectPieceOfFurnitureById(Integer.parseInt(idUpdate))).updatePieceOfFurniture("id_line", id_lineField);
                }
                if (!confidentiality_levelField.equals("")) {
                    Objects.requireNonNull(PieceOfFurniture.selectPieceOfFurnitureById(Integer.parseInt(idUpdate))).updatePieceOfFurniture("confidentiality_level", confidentiality_levelField);
                }
                changeViewer();
            }
            else {
                animation1.playAnimation();
                animation2.playAnimation();
                animation3.playAnimation();
                animation4.playAnimation();
                animation5.playAnimation();
                animation6.playAnimation();
            }
        }
        catch (Exception e) {
            animation1.playAnimation();
            animation2.playAnimation();
            animation3.playAnimation();
            animation4.playAnimation();
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
        id_of_piece.setCellValueFactory(new PropertyValueFactory<>("id_of_piece"));
        piece_type.setCellValueFactory(new PropertyValueFactory<>("piece_type"));
        article_number.setCellValueFactory(new PropertyValueFactory<>("article_number"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        id_line.setCellValueFactory(new PropertyValueFactory<>("id_line"));
        confidentiality_level.setCellValueFactory(new PropertyValueFactory<>("confidentiality_level"));

        tablePieces.setItems(PieceOfFurniture.selectAllPiecesOfFurniture());
    }

}
