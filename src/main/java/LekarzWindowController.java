import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

import classes.*;
import com.google.protobuf.Value;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class LekarzWindowController {
    private ObservableList<?> list= FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnSzukajPacjenta;

    @FXML
    private Button btnFiltruj;

    @FXML
    private Button btnZmienStatus;

    @FXML
    private TableColumn<Archiwum, String> chorobaArchiwum;

    @FXML
    private TableColumn<Szczepienia, String> chorobaStatus;

    @FXML
    private TableColumn<Archiwum, String> chorobaStatystyka;

    @FXML
    private TextField dataDo;

    @FXML
    private TextField dataOd;

    @FXML
    private TableColumn<Szczepienia, Date> dataStatus;

    @FXML
    private TableColumn<Archiwum, Date> datatArchiwum;

    @FXML
    private TableColumn<Archiwum, Time> godzinaArchiwum;

    @FXML
    private TableColumn<Szczepienia, Time> godzinaStatus;

    @FXML
    private TextField idTyp;

    @FXML
    private TableColumn<Archiwum, String> idTypArchiwum;

    @FXML
    private TableColumn<Szczepienia, String> idTypStatus;

    @FXML
    private TableColumn<Archiwum, Integer> ilośćWykonanychStatystyka;

    @FXML
    private TextField peselPac;

    @FXML
    private ChoiceBox<Status> sortujPoList;

    @FXML
    private TableView  tblStatystyki;

    @FXML
    private TableView  tblArchiwum;

    @FXML
    private TableView  tblStatusZmiana;

    @FXML
    void btnClickedSzukajPacjenta(ActionEvent event) throws SQLException {
        tblStatusZmiana.getItems().clear();
        if(!peselPac.getText().equals(null)){
            Connection connection = DriverManager.getConnection("", "admin", "admin1");
            PreparedStatement selectAll = connection.prepareStatement("select * from dostepne_lekarz where pesel =" + peselPac.getText() + ";");
            ResultSet rsAll = selectAll.executeQuery();
            while(rsAll.next()){
                String answerPesel = rsAll.getString(1);
                String answerNazwa = rsAll.getString(2);
                String answerChoroba = rsAll.getString(3);
                String answerData = rsAll.getString(4);
                String answerGodzina = rsAll.getString(5);
                String answerStatus = rsAll.getString(6);

                // zrobić klasę do zbierania wartości
//                list.addAll(answerPesel, answerNazwa, answerChoroba, answerData, answerGodzina, answerStatus);
//                chorobaStatus.setCellValueFactory(new PropertyValueFactory<>("Choroba"));
//                dataStatus.setCellFactory(new PropertyValueFactory<>("Data"));
//                godzinaStatus.getCellFactory(new PropertyValueFactory<>("Godzina"));
//                idTypStatus.getCellFactory(new PropertyValueFactory<>("Typ"));

            }
        }

    }

    @FXML
    void btnClickedFiltruj(ActionEvent event) {

    }

    @FXML
    void btnClickedZmienStatus(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnSzukajPacjenta != null : "fx:id=\"btnSzukajPacjenta\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert btnFiltruj != null : "fx:id=\"btnFiltruj\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert btnZmienStatus != null : "fx:id=\"btnZmienStatus\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert chorobaArchiwum != null : "fx:id=\"chorobaArchiwum\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert chorobaStatus != null : "fx:id=\"chorobaStatus\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert chorobaStatystyka != null : "fx:id=\"chorobaStatystyka\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert dataDo != null : "fx:id=\"dataDo\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert dataOd != null : "fx:id=\"dataOd\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert dataStatus != null : "fx:id=\"dataStatus\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert datatArchiwum != null : "fx:id=\"datatArchiwum\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert godzinaArchiwum != null : "fx:id=\"godzinaArchiwum\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert godzinaStatus != null : "fx:id=\"godzinaStatus\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert idTyp != null : "fx:id=\"idTyp\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert idTypArchiwum != null : "fx:id=\"idTypArchiwum\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert idTypStatus != null : "fx:id=\"idTypStatus\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert ilośćWykonanychStatystyka != null : "fx:id=\"ilośćWykonanychStatystyka\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert peselPac != null : "fx:id=\"peselPac\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert sortujPoList != null : "fx:id=\"sortujPoList\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert tblStatystyki != null : "fx:id=\"tableStatystyki\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert tblArchiwum != null : "fx:id=\"tblArchiwum\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert tblStatusZmiana != null : "fx:id=\"tblStatusZmiana\" was not injected: check your FXML file 'lekarzwindow.fxml'.";

    }

}
