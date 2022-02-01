import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ResourceBundle;

import classes.DostepneSzczepienia;
import classes.LekarzArchiwum;
import classes.LekarzStatus;
import classes.LekarzStatystyki;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class LekarzWindowController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnFiltruj;

    @FXML
    private Button btnSzukajPacjenta;

    @FXML
    private Button btnZmienStatus;

    @FXML
    private TableColumn<LekarzArchiwum, String> colArchiwumChoroba;

    @FXML
    private TableColumn<LekarzArchiwum, Date> colArchiwumData;

    @FXML
    private TableColumn<LekarzArchiwum, Time> colArchiwumGodzina;

    @FXML
    private TableColumn<LekarzArchiwum, String> colArchiwumNazwa;

    @FXML
    private TableColumn<LekarzStatus, String> colStatusChoroba;

    @FXML
    private TableColumn<LekarzStatus, Date> colStatusData;

    @FXML
    private TableColumn<LekarzStatus, Time> colStatusGodzina;

    @FXML
    private TableColumn<LekarzStatus, String> colStatusNazwa;

    @FXML
    private TableColumn<LekarzStatus, String> colStatusPesel;

    @FXML
    private TableColumn<LekarzStatystyki, String> colStatystykiChoroba;

    @FXML
    private TableColumn<LekarzStatystyki, Integer> colStatystykiIlosc;

    @FXML
    private TableColumn<LekarzStatystyki, String> colStatystykiNazwa;

    @FXML
    private TableView<LekarzArchiwum> tblArchiwum;

    @FXML
    private TableView<LekarzStatus> tblStatusZmiana;

    @FXML
    private TableView<LekarzStatystyki> tblStatystyki;

    @FXML
    private TextField txtDataDo;

    @FXML
    private TextField txtDataOd;

    @FXML
    private TextField txtNazwa;

    @FXML
    private TextField txtPesel;

    @FXML
    private TextField txtStatus;

    @FXML
    private TextField txtGodzinaStatus;

    @FXML
    private DatePicker dateDO;

    @FXML
    private DatePicker dateOD;

    @FXML
    private DatePicker dateStatus;

    private LekarzDAO lekarzDAO;
    private DatabaseConnection databaseConnection;
    private Stage stage;
    private Scene scene;

    public void setLekarzDAO(LekarzDAO lekarzDAO){
        this.lekarzDAO = lekarzDAO;
    }

    private void receiveData(ActionEvent event){
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        this.lekarzDAO = (LekarzDAO) stage.getUserData();
        String userName = lekarzDAO.getUserName();
        String userPassword = lekarzDAO.getUserPassword();
        Integer nrPWZ = lekarzDAO.getNrPwz();
    }

    @FXML
    void btnClickedFiltruj(ActionEvent event) throws SQLException {
        receiveData(event);
        this.tblArchiwum.getItems().clear();
        Date dataOdSql = Date.valueOf(dateOD.getValue());
        Date dataDoSql = Date.valueOf(dateDO.getValue());
        ObservableList<LekarzArchiwum> lekarzArchiwumObservableList = this.lekarzDAO.showSpecifiedFromArchiwum(dataOdSql,dataDoSql);
        colArchiwumNazwa.setCellValueFactory(new PropertyValueFactory<LekarzArchiwum, String>("nazwaLekarzArchiwum"));
        colArchiwumChoroba.setCellValueFactory(new PropertyValueFactory<LekarzArchiwum, String>("chorobaLekarzArchiwum"));;
        colArchiwumData.setCellValueFactory(new PropertyValueFactory<LekarzArchiwum, Date>("dataLekarzArchiwum"));;
        colArchiwumGodzina.setCellValueFactory(new PropertyValueFactory<LekarzArchiwum, Time>("godzinaLekarzRealizacja"));;

        this.tblStatystyki.getItems().clear();
        ObservableList<LekarzStatystyki> lekarzStatystyki = this.lekarzDAO.showLekarzStatystyki();
        colStatystykiChoroba.setCellValueFactory(new PropertyValueFactory<LekarzStatystyki, String>("chorobaLekarzStatystyki"));
        colStatystykiNazwa.setCellValueFactory(new PropertyValueFactory<LekarzStatystyki, String>("nazwaLekarzStatystyki"));
        colStatystykiIlosc.setCellValueFactory(new PropertyValueFactory<LekarzStatystyki, Integer>("iloscWykonanychLekarzStatystyki"));
        // jeszcze część odnośnie statystyki \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        this.tblArchiwum.setItems(lekarzArchiwumObservableList);
    }

    @FXML
    void btnClickedSzukajPacjenta(ActionEvent event) {
        String pesel = txtPesel.getText().toString();
        receiveData(event);
        this.tblStatusZmiana.getItems().clear();
        ObservableList<LekarzStatus> lekarzStatus = this.lekarzDAO.showPacjenta(pesel);
        colStatusNazwa.setCellValueFactory(new PropertyValueFactory<LekarzStatus, String>("nazwaLekarzStatus"));
        colStatusChoroba.setCellValueFactory(new PropertyValueFactory<LekarzStatus, String>("chorobaLekarzStatus"));
        colStatusData.setCellValueFactory(new PropertyValueFactory<LekarzStatus, Date>("dataLekarzRealizacja"));
        colStatusGodzina.setCellValueFactory(new PropertyValueFactory<LekarzStatus, Time>("godzinaLekarzRealizacja"));

        this.tblStatusZmiana.setItems(lekarzStatus);

    }

    @FXML
    void btnClickedZmienStatus(ActionEvent event) {
        receiveData(event);
        String nazwa = txtNazwa.getText().toString();
        String status = txtStatus.getText().toString();
        Date data = Date.valueOf(dateStatus.getValue());
        Time godzina = Time.valueOf(txtGodzinaStatus.getText().toString());
        try{
            CallableStatement cstmUpdate = databaseConnection.getDatabaseLink().prepareCall("update szczepienia set status = " + status + " where " +
                    " nazwa = " + nazwa + " and data = " + data + " and godzina = " + godzina + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void initialize() {
        assert btnFiltruj != null : "fx:id=\"btnFiltruj\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert btnSzukajPacjenta != null : "fx:id=\"btnSzukajPacjenta\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert btnZmienStatus != null : "fx:id=\"btnZmienStatus\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert colArchiwumChoroba != null : "fx:id=\"colArchiwumChoroba\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert colArchiwumData != null : "fx:id=\"colArchiwumData\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert colArchiwumGodzina != null : "fx:id=\"colArchiwumGodzina\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert colArchiwumNazwa != null : "fx:id=\"colArchiwumNazwa\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert colStatusChoroba != null : "fx:id=\"colStatusChoroba\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert colStatusData != null : "fx:id=\"colStatusData\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert colStatusGodzina != null : "fx:id=\"colStatusGodzina\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert colStatusNazwa != null : "fx:id=\"colStatusNazwa\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert colStatusPesel != null : "fx:id=\"colStatusPesel\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert colStatystykiChoroba != null : "fx:id=\"colStatystykiChoroba\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert colStatystykiIlosc != null : "fx:id=\"colStatystykiIlosc\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert colStatystykiNazwa != null : "fx:id=\"colStatystykiNazwa\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert tblArchiwum != null : "fx:id=\"tblArchiwum\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert tblStatusZmiana != null : "fx:id=\"tblStatusZmiana\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert tblStatystyki != null : "fx:id=\"tblStatystyki\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert txtNazwa != null : "fx:id=\"txtNazwa\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert txtPesel != null : "fx:id=\"txtPesel\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert txtStatus != null : "fx:id=\"txtStatus\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert dateDO != null : "fx:id=\"dateDO\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert dateOD != null : "fx:id=\"dateOD\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert dateStatus != null : "fx:id=\"dateStatus\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert txtGodzinaStatus != null : "fx:id=\"txtGodzinaStatus\" was not injected: check your FXML file 'lekarzwindow.fxml'.";

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
