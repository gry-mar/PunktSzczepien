import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import classes.DostepneSzczepienia;
import classes.LekarzArchiwum;
import classes.LekarzStatus;
import classes.LekarzStatystyki;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LekarzWindowController{
    /**
     * class to interact with lekarz view
     * @author Zofia Dobrowolska
     * @version 1.0
     * @since 04.02.2022
     */

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CategoryAxis axisMiesiace;

    @FXML
    private NumberAxis axisSzczepionki;

    @FXML
    private BarChart<String, Number> charWykres;

    @FXML
    private Button btnWykres;

    @FXML
    private Button btnFiltruj;

    @FXML
    private Button btnWyloguj;

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
    private TextField txtStatusNazwa;

    @FXML
    private TextField txtStatusRok;

    @FXML
    private DatePicker dateDO;

    @FXML
    private DatePicker dateOD;

    @FXML
    private DatePicker dateStatus;

    @FXML
    private Text tfArchiwumError;

    @FXML
    private Text tfStatusError;

    private LekarzDAO lekarzDAO;
    private DatabaseConnection databaseConnection;
    private Stage stage;
    private Scene scene;
    private UserHolder userHolder = new UserHolder(null, null);

    public void setLekarzDAO(LekarzDAO lekarzDAO){
        this.lekarzDAO = lekarzDAO;
    }

    /**
     * method that intearacts with table views, both archiwum and statystyki
     * @param event
     * @throws SQLException
     */
    @FXML
    void btnClickedFiltruj(ActionEvent event) throws SQLException {
        try{
        this.tblArchiwum.getItems().clear();
        Date dataOdSql = Date.valueOf(dateOD.getValue());
        Date dataDoSql = Date.valueOf(dateDO.getValue());
        ObservableList<LekarzArchiwum> lekarzArchiwumObservableList = this.lekarzDAO.showSpecifiedFromArchiwum(dataOdSql, dataDoSql);
        colArchiwumNazwa.setCellValueFactory(new PropertyValueFactory<LekarzArchiwum, String>("nazwaLekarzArchiwum"));
        colArchiwumChoroba.setCellValueFactory(new PropertyValueFactory<LekarzArchiwum, String>("chorobaLekarzArchiwum"));;
        colArchiwumData.setCellValueFactory(new PropertyValueFactory<LekarzArchiwum, Date>("dataLekarzArchiwum"));;
        colArchiwumGodzina.setCellValueFactory(new PropertyValueFactory<LekarzArchiwum, Time>("godzinaLekarzArchiwum"));;

        this.tblStatystyki.getItems().clear();
        ObservableList<LekarzStatystyki> lekarzStatystyki = this.lekarzDAO.showLekarzStatystyki();
        colStatystykiChoroba.setCellValueFactory(new PropertyValueFactory<LekarzStatystyki, String>("chorobaLekarzStatystyki"));
        colStatystykiNazwa.setCellValueFactory(new PropertyValueFactory<LekarzStatystyki, String>("nazwaLekarzStatystyki"));
        colStatystykiIlosc.setCellValueFactory(new PropertyValueFactory<LekarzStatystyki, Integer>("iloscWykonanychLekarzStatystyki"));

        this.tblArchiwum.setItems(lekarzArchiwumObservableList);
        this.tblStatystyki.setItems(lekarzStatystyki);}
        catch(NullPointerException e){
            tfArchiwumError.setText("Wybierz zakres dat");
        }
    }

    /**
     * Interacts with table tblStatusZmiana,
     * need to fill textField, shows vaccines for specific patient
     * @param event
     */
    @FXML
    void btnClickedSzukajPacjenta(ActionEvent event) {
        try{
        String pesel = txtPesel.getText().toString();
        this.tblStatusZmiana.getItems().clear();
        ObservableList<LekarzStatus> lekarzStatus = this.lekarzDAO.showPacjenta(pesel);
        colStatusNazwa.setCellValueFactory(new PropertyValueFactory<LekarzStatus, String>("nazwaLekarzStatus"));
        colStatusChoroba.setCellValueFactory(new PropertyValueFactory<LekarzStatus, String>("chorobaLekarzStatus"));
        colStatusData.setCellValueFactory(new PropertyValueFactory<LekarzStatus, Date>("dataLekarzStatus"));
        colStatusGodzina.setCellValueFactory(new PropertyValueFactory<LekarzStatus, Time>("godzinaLekarzStatus"));

        this.tblStatusZmiana.setItems(lekarzStatus);}
        catch(NullPointerException e){
            tfStatusError.setText("Wprowad?? pesel pacjenta");
        }

    }

    /**
     * Changes vaccination status by interacting with gui
     * @param event
     */

    @FXML
    void btnClickedZmienStatus(ActionEvent event) {
       try{
        String nazwa = txtNazwa.getText().toString();
        String status = txtStatus.getText().toString();
        Date data = Date.valueOf(dateStatus.getValue());
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        Time godzina = Time.valueOf(txtGodzinaStatus.getText().toString());
        if(status.equals("zrealizowane")||status.equals("niezrezalizowane")){
        this.lekarzDAO.lekarzUpdate(status, nazwa, data, godzina);}
       else{
           tfStatusError.setText("Wprowadzono nieprawid??owy status");
        }}
       catch (SQLException throwables) {
           throwables.printStackTrace();
       }catch(NullPointerException e){
           tfStatusError.setText("Wprowad?? wszystkie dane");
       }

    }

    /**
     * log out method,
     * changes scene to logWindow
     * @param event
     * @throws IOException
     */

    @FXML
    void ClickedWyloguj(ActionEvent event) throws IOException, SQLException {

        btnFiltruj.getScene().getWindow().hide();
        Parent root=  FXMLLoader.load(getClass().getResource("logwindow.fxml"));
        Stage primaryStage = new Stage();
        scene= new Scene(root,600,400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * methode to show statistics chart
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @FXML
    void ClickedWykres(ActionEvent event) throws SQLException, ClassNotFoundException {
        charWykres.getData().clear();
        try {
            int rok = Integer.parseInt(txtStatusRok.getText());
            String nazwa = txtStatusNazwa.getText().toString();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.getData().add(new XYChart.Data<>("1", lekarzDAO.szczepieniaMiesiac(nazwa, rok, 1)));
            series.getData().add(new XYChart.Data<>("2", lekarzDAO.szczepieniaMiesiac(nazwa, rok, 2)));
            series.getData().add(new XYChart.Data<>("3", lekarzDAO.szczepieniaMiesiac(nazwa, rok, 3)));
            series.getData().add(new XYChart.Data<>("4", lekarzDAO.szczepieniaMiesiac(nazwa, rok, 4)));
            series.getData().add(new XYChart.Data<>("5", lekarzDAO.szczepieniaMiesiac(nazwa, rok, 5)));
            series.getData().add(new XYChart.Data<>("6", lekarzDAO.szczepieniaMiesiac(nazwa, rok, 6)));
            series.getData().add(new XYChart.Data<>("7", lekarzDAO.szczepieniaMiesiac(nazwa, rok, 7)));
            series.getData().add(new XYChart.Data<>("8", lekarzDAO.szczepieniaMiesiac(nazwa, rok, 8)));
            series.getData().add(new XYChart.Data<>("9", lekarzDAO.szczepieniaMiesiac(nazwa, rok, 9)));
            series.getData().add(new XYChart.Data<>("10", lekarzDAO.szczepieniaMiesiac(nazwa, rok, 10)));
            series.getData().add(new XYChart.Data<>("11", lekarzDAO.szczepieniaMiesiac(nazwa, rok, 11)));
            series.getData().add(new XYChart.Data<>("12", lekarzDAO.szczepieniaMiesiac(nazwa, rok, 12)));
            charWykres.getData().add(series);
        } catch (Exception e){
            tfArchiwumError.setText("Wprowad?? wszystkie warto??ci!");
        }

    }

    /**
     * initialization, receive data from singleton
     */
    @FXML
    void initialize() {
        assert btnFiltruj != null : "fx:id=\"btnFiltruj\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert btnWyloguj != null : "fx:id=\"btnWyloguj\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
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
        assert tfArchiwumError != null : "fx:id=\"tfArchiwumError\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert tfStatusError != null : "fx:id=\"tfStatusError\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert txtStatusNazwa != null : "fx:id=\"txtStatusNazwa\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert txtStatusRok != null : "fx:id=\"txtStatusRok\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert charWykres != null : "fx:id=\"charWykres\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert btnWykres != null : "fx:id=\"btnWykres\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert axisMiesiace != null : "fx:id=\"axisMiesiace\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        assert axisSzczepionki != null : "fx:id=\"axisSzczepionki\" was not injected: check your FXML file 'lekarzwindow.fxml'.";
        charWykres.getData().clear();
        userHolder = UserHolder.getInstance();
        String login = UserHolder.getLogin();
        String haslo = UserHolder.getHaslo();
        lekarzDAO = new LekarzDAO(login,haslo);
        databaseConnection = lekarzDAO.getDatabaseConnection();
        databaseConnection.getConnection();


    }


}
