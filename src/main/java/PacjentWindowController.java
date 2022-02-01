import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import classes.ArchiwumPacjent;
import classes.DostepneSzczepienia;
import classes.RealizacjaPacjent;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PacjentWindowController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnPokazArchiwum;

    @FXML
    private Button btnPokazDost;

    @FXML
    private Button btnPokazNadchodzace;

    @FXML
    private Button btnWyloguj;

    @FXML
    private TextField chooseChoroba;


    @FXML
    private TableColumn<ArchiwumPacjent, String> chorobaArchiwumCol;

    @FXML
    private TableColumn<DostepneSzczepienia, String> chorobaDostepneCol;

    @FXML
    private TableColumn<RealizacjaPacjent, String> chorobaWRealizacjiCol;

    @FXML
    private Text czyZapisano;

    @FXML
    private TableColumn<ArchiwumPacjent, Date> dataArchiwumCol;

    @FXML
    private DatePicker dataDoArchiwum;

    @FXML
    private TableColumn<DostepneSzczepienia, Date> dataDostepneCol;

    @FXML
    private DatePicker dataNa;

    @FXML
    private DatePicker dataOdArchiwum;

    @FXML
    private TableColumn<RealizacjaPacjent, Date> dataWRealizacjiCol;

    @FXML
    private DatePicker dataZ;

    @FXML
    private DatePicker dataZapisuChooser;

    @FXML
    private TableColumn<ArchiwumPacjent, Time> godzinaArchiwumCol;

    @FXML
    private TableColumn<DostepneSzczepienia, Time> godzinaDostepneCol;

    @FXML
    private TextField godzinaNa;

    @FXML
    private TableColumn<RealizacjaPacjent, Time> godzinaWRealizacjiCol;

    @FXML
    private TextField godzinaZ;

    @FXML
    private TextField godzinaZapisu;

    @FXML
    private TableColumn<ArchiwumPacjent, String> nazwaArchiwumCol;

    @FXML
    private TableColumn<DostepneSzczepienia, String> nazwaDostepneCol;

    @FXML
    private TableColumn<RealizacjaPacjent, String> nazwaWRealizacjiCol;

    @FXML
    private TableView<ArchiwumPacjent> tableArchiwum;

    @FXML
    private TableView<DostepneSzczepienia> tableDostepne;

    @FXML
    private TableView<?> tableWRealizacji;

    @FXML
    private Button zapiszNaSzczepienie;

    @FXML
    private Button zmienTermin;

    private PacjentDAO pacjentDAO;
    private DatabaseConnection databaseConnection;
    private Stage stage;
    private Scene scene;


    public void setPacjentDAO(PacjentDAO pacjentDAO) {
        this.pacjentDAO = pacjentDAO;
    }

    private void receiveData(ActionEvent event){
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        this.pacjentDAO = (PacjentDAO) stage.getUserData();
        String userName = pacjentDAO.getUserName();
        String userPassword = pacjentDAO.getUserPassword();
        String pesel = pacjentDAO.getPesel();

    }

    @FXML
    void pokazArchiwumClicked(ActionEvent event) {
        receiveData(event);
        this.tableArchiwum.getItems().clear();
//        pacjentDAO.getPesel();
////        pacjentDAO.getUserName();
////        pacjentDAO.getUserPassword();
        Date dataOdSql = Date.valueOf(dataOdArchiwum.getValue());
        Date dataDoSql = Date.valueOf(dataDoArchiwum.getValue());
        System.out.println(dataDoSql+","+dataOdSql);
        ObservableList<ArchiwumPacjent> archiwumPacjentObservableList =
                this.pacjentDAO.showSpecifiedFromArchiwum(dataOdSql,dataDoSql);
        nazwaArchiwumCol.setCellValueFactory(new PropertyValueFactory<ArchiwumPacjent,String>("Nazwa"));
        chorobaArchiwumCol.setCellValueFactory(new PropertyValueFactory<ArchiwumPacjent,String>("Choroba"));
        dataArchiwumCol.setCellValueFactory(new PropertyValueFactory<ArchiwumPacjent,Date>("Data"));
        godzinaArchiwumCol.setCellValueFactory(new PropertyValueFactory<ArchiwumPacjent,Time>("Godzina"));


        this.tableArchiwum.setItems(archiwumPacjentObservableList);
        System.out.println(archiwumPacjentObservableList.toString());
        System.out.println(archiwumPacjentObservableList.get(0).getChoroba().toString());


    }

    @FXML
    void pokazDostepneClicked(ActionEvent event) {
        receiveData(event);
        ObservableList<DostepneSzczepienia> dostepneSzczepienia = this.pacjentDAO.showAllDostepne();
        nazwaDostepneCol.setCellValueFactory(new PropertyValueFactory<DostepneSzczepienia,String>("nazwaDostepne"));
        chorobaDostepneCol.setCellValueFactory(new PropertyValueFactory<DostepneSzczepienia,String>("chorobaDostepne"));
        dataDostepneCol.setCellValueFactory(new PropertyValueFactory<DostepneSzczepienia, Date>("dataDostepne"));
        godzinaDostepneCol.setCellValueFactory(new PropertyValueFactory<DostepneSzczepienia, Time>("godzinaDostepne"));
        this.tableDostepne.setItems(dostepneSzczepienia);

    }

    @FXML
    void pokazNadchodzaceClicked(ActionEvent event) {
        receiveData(event);
        ObservableList<RealizacjaPacjent> realizacja = this.pacjentDAO.showAllRealizacja();
        nazwaWRealizacjiCol.setCellValueFactory(new PropertyValueFactory<RealizacjaPacjent,String>("nazwaRealizacja"));
        chorobaWRealizacjiCol.setCellValueFactory(new PropertyValueFactory<RealizacjaPacjent,String>("chorobaRealizacja"));
        dataWRealizacjiCol.setCellValueFactory(new PropertyValueFactory<RealizacjaPacjent, Date>("dataRealizacja"));
        godzinaWRealizacjiCol.setCellValueFactory(new PropertyValueFactory<RealizacjaPacjent, Time>("godzinaRealizacja"));

    }

    @FXML
    void wylogujClicked(ActionEvent event) throws IOException, SQLException {
        btnPokazArchiwum.getScene().getWindow().hide();
        Parent root=  FXMLLoader.load(getClass().getResource("logwindow.fxml"));
        Stage primaryStage = new Stage();
        scene= new Scene(root,1000,800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void zapisNaSzczepienieClicked(ActionEvent event) throws ParseException {
        receiveData(event);
        Date dataZapisu = Date.valueOf(dataZapisuChooser.getValue());
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        long parseTime = sdf.parse(godzinaZapisu.getText().toString()).getTime();
        Time godzina = new Time(parseTime);
        String choroba = chooseChoroba.getText().toString();
        boolean czyZapisano = pacjentDAO.zapisPacjenta(dataZapisu,godzina,choroba);

        System.out.println(czyZapisano);


    }

    @FXML
    void zmianaTerminuClicked(ActionEvent event) throws ParseException {
        receiveData(event);
        Date dataPocz = Date.valueOf(dataZ.getValue());
        Date dataKonc = Date.valueOf(dataNa.getValue());
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        long parseZ = sdf.parse(godzinaZ.getText().toString()).getTime();
        long parseNa = sdf.parse(godzinaNa.getText().toString()).getTime();
        Time godzinaPocz = new Time(parseZ);
        Time godzinaKonc = new Time(parseNa);






    }

    @FXML
    void initialize() {
        assert btnPokazArchiwum != null : "fx:id=\"btnPokazArchiwum\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert btnPokazDost != null : "fx:id=\"btnPokazDost\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert btnPokazNadchodzace != null : "fx:id=\"btnPokazNadchodzace\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert btnWyloguj != null : "fx:id=\"btnWyloguj\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert chooseChoroba != null : "fx:id=\"chooseChoroba\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert chorobaArchiwumCol != null : "fx:id=\"chorobaArchiwumCol\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert chorobaDostepneCol != null : "fx:id=\"chorobaDostepneCol\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert chorobaWRealizacjiCol != null : "fx:id=\"chorobaWRealizacjiCol\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert czyZapisano != null : "fx:id=\"czyZapisano\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert dataArchiwumCol != null : "fx:id=\"dataArchiwumCol\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert dataDoArchiwum != null : "fx:id=\"dataDoArchiwum\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert dataDostepneCol != null : "fx:id=\"dataDostepneCol\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert dataNa != null : "fx:id=\"dataNa\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert dataOdArchiwum != null : "fx:id=\"dataOdArchiwum\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert dataWRealizacjiCol != null : "fx:id=\"dataWRealizacjiCol\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert dataZ != null : "fx:id=\"dataZ\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert dataZapisuChooser != null : "fx:id=\"dataZapisuChooser\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert godzinaArchiwumCol != null : "fx:id=\"godzinaArchiwumCol\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert godzinaDostepneCol != null : "fx:id=\"godzinaDostepneCol\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert godzinaNa != null : "fx:id=\"godzinaNa\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert godzinaWRealizacjiCol != null : "fx:id=\"godzinaWRealizacjiCol\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert godzinaZ != null : "fx:id=\"godzinaZ\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert godzinaZapisu != null : "fx:id=\"godzinaZapisu\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert nazwaArchiwumCol != null : "fx:id=\"nazwaArchiwumCol\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert nazwaDostepneCol != null : "fx:id=\"nazwaDostepneCol\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert nazwaWRealizacjiCol != null : "fx:id=\"nazwaWRealizacjiCol\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert tableArchiwum != null : "fx:id=\"tableArchiwum\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert tableDostepne != null : "fx:id=\"tableDostepne\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert tableWRealizacji != null : "fx:id=\"tableWRealizacji\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert zapiszNaSzczepienie != null : "fx:id=\"zapiszNaSzczepienie\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert zmienTermin != null : "fx:id=\"zmienTermin\" was not injected: check your FXML file 'pacjentwindow.fxml'.";


        databaseConnection.getConnection();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
