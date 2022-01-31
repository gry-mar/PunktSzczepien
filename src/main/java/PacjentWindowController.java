import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import classes.ArchiwumPacjent;
import classes.DostepneSzczepienia;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private Button btnSort;

    @FXML
    private Button btnWyloguj;

    @FXML
    private ComboBox<String> cBoxSortujPo;

    @FXML
    private TableColumn<ArchiwumPacjent, String> chorobaArchiwumCol;

    @FXML
    private TableColumn<DostepneSzczepienia, String> chorobaDostepneCol;

    @FXML
    private TableColumn<?, ?> chorobaWRealizacjiCol;

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
    private TableColumn<?, ?> dataWRealizacjiCol;

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
    private TableColumn<?, ?> godzinaWRealizacjiCol;

    @FXML
    private TextField godzinaZ;

    @FXML
    private TextField godzinaZapisu;

    @FXML
    private TableColumn<ArchiwumPacjent, String> nazwaArchiwumCol;

    @FXML
    private TableColumn<DostepneSzczepienia, String> nazwaDostepneCol;

    @FXML
    private TableColumn<?, ?> nazwaWRealizacjiCol;

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
        this.tableArchiwum.setItems(archiwumPacjentObservableList);
        System.out.println(archiwumPacjentObservableList.toString());


    }

    @FXML
    void sortBtnClicked(ActionEvent event) {
        //cBoxSortujPo.getItems().addAll("nazwa", "choroba", "data", "godzina");
        tableDostepne.getSortOrder().get(0).textProperty().bindBidirectional(cBoxSortujPo.valueProperty());

    }

    @FXML
    void wylogujClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root=  FXMLLoader.load(getClass().getResource("logwindow.fxml"));
        stage.setUserData(pacjentDAO);
        scene= new Scene(root,1000,800);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void zapisNaSzczepienieClicked(ActionEvent event) {
            receiveData(event);
            this.tableDostepne.getItems().clear();
    }

    @FXML
    void zmianaTerminuClicked(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnPokazArchiwum != null : "fx:id=\"btnPokazArchiwum\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert btnSort != null : "fx:id=\"btnSort\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert btnWyloguj != null : "fx:id=\"btnWyloguj\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
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
        assert cBoxSortujPo != null : "fx:id=\"cBoxSortujPo\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        databaseConnection.getConnection();
        cBoxSortujPo.getItems().addAll("nazwa", "choroba", "data", "godzina");

        ObservableList<DostepneSzczepienia> dostepneSzczepienias = this.pacjentDAO.showAllDostepne();
        this.tableDostepne.setItems(dostepneSzczepienias);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
