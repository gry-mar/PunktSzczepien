import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import classes.Lekarz;
import classes.Szczepienia;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminWindowController {
    /**
     * class to interact with admin view
     * @author Martyna Grygiel
     * @version 1.0
     * @since 04.02.2022
     */

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnDodajLekarza;

    @FXML
    private Button btnDodajTermin;

    @FXML
    private Button btnPokazLekarzy;

    @FXML
    private Button btnPokazTerminy;

    @FXML
    private Button btnWyloguj;

    @FXML
    private TableColumn<Szczepienia, Date> dataSzczCol;

    @FXML
    private TextField dodajGodzine;

    @FXML
    private DatePicker dtPickerDodajDate;

    @FXML
    private TableColumn<Szczepienia, Time> godzinaSzczCol;

    @FXML
    private TableColumn<Lekarz, String> imieLekarzCol;

    @FXML
    private TextField lekarzHaslo;

    @FXML
    private TextField lekarzImie;

    @FXML
    private TextField lekarzLogin;

    @FXML
    private TextField lekarzNazwisko;

    @FXML
    private TextField lekarzNrPwz;

    @FXML
    private TableColumn<Lekarz, String> loginLekarzCol;

    @FXML
    private TableColumn<Lekarz, String> nazwiskoLekarzCol;

    @FXML
    private TableColumn<Lekarz, Integer> nrPwzCol;

    @FXML
    private TableColumn<Szczepienia, Integer> nrPwzSzczCol;

    @FXML
    private TableColumn<Szczepienia, String> peselPacSzczCol;

    @FXML
    private TableColumn<Szczepienia, String> statusCol;

    @FXML
    private TableColumn<Szczepienia, String> typSzczCol;

    @FXML
    private TableView<Lekarz> tableLekarz;

    @FXML
    private TableView<Szczepienia> tableSzczepienia;

    @FXML
    private Text tvDodawanieLekarza;

    @FXML
    private Text tvDodawanieTerminu;

    @FXML
    private TextField tfChorobaAdd;

    @FXML
    private TextField nrPwzLekarzaDodaj;

    @FXML
    private TextField tfNazwaSzczepionki;


    private Scene scene;
    private Stage stage;
    private AdminDAO adminDAO;
    private DatabaseConnection databaseConnection;
    private UserHolder userHolder;

    public void setAdminDAO(AdminDAO adminDAO){
        this.adminDAO = adminDAO;
    }


    /**
     * Method that interacts with GUI
     * responsible for adding new doctor
     * @param event
     */
    @FXML
    void dodajLekarzaClicked(ActionEvent event) {
        try {
            String imie = lekarzImie.getText().toString();
            String nazwisko = lekarzNazwisko.getText().toString();
            int nrPwz = Integer.valueOf(lekarzNrPwz.getText());
            String login = lekarzLogin.getText().toString();
            String haslo = lekarzHaslo.getText().toString();
            boolean czyDodano = adminDAO.zapisLekarza(imie, nazwisko, nrPwz, login, haslo);
            if (czyDodano) {
                tvDodawanieLekarza.setText("Poprawnie dodano lekarza");
            } else {
                tvDodawanieLekarza.setText("Wystąpił błąd przy dodawaniu");
            }
        }catch(NullPointerException e){
            tvDodawanieLekarza.setText("Wprowadż wszystkie dane");
        }


    }

    /**
     * Method responsible for adding vaccination date
     * @param event
     * @throws ParseException
     */

    @FXML
    void dodajTerminClicked(ActionEvent event) throws ParseException {
try {
    Date data = Date.valueOf(dtPickerDodajDate.getValue());
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
    long parseZ = sdf.parse(dodajGodzine.getText().toString()).getTime();
    Time godzina = new Time(parseZ);
    String choroba = tfChorobaAdd.getText().toString();
    String nazwaSzczepionki = tfNazwaSzczepionki.getText().toString();
    int nrPwz = Integer.parseInt(nrPwzLekarzaDodaj.getText().toString());
    boolean czyDodano = this.adminDAO.dodajTermin(data, godzina, choroba, nrPwz, nazwaSzczepionki);
    if (czyDodano) {
        tvDodawanieTerminu.setText("Poprawnie dodano trermin");
    } else {
        tvDodawanieTerminu.setText("Błąd przy dodawaniu, sprawdź dane");
    }
}catch(NullPointerException e){
    tvDodawanieTerminu.setText("Wprowadź wszystkie dane");
}

    }

    /**
     * method that shows doctors in GUI table
     * @param event
     */
    @FXML
    void pokazLekarzyClicked(ActionEvent event) {
        ObservableList<Lekarz> lekarze = this.adminDAO.showAllLekarze();
        imieLekarzCol.setCellValueFactory(new PropertyValueFactory<Lekarz,String>("imie"));
        nazwiskoLekarzCol.setCellValueFactory(new PropertyValueFactory<Lekarz,String>("nazwisko"));
        nrPwzCol.setCellValueFactory(new PropertyValueFactory<Lekarz,Integer>("nrPwz"));
        loginLekarzCol.setCellValueFactory(new PropertyValueFactory<Lekarz,String>("loginLekarz"));
        this.tableLekarz.setItems(lekarze);




    }

    /**
     * method that shows vaccination dates in GUI table
     * @param event
     */

    @FXML
    void pokazTerminyClicked(ActionEvent event) {
        ObservableList<Szczepienia> szczepienia = this.adminDAO.showAllSzczepienia();
        dataSzczCol.setCellValueFactory(new PropertyValueFactory<Szczepienia, Date>("data"));
        godzinaSzczCol.setCellValueFactory(new PropertyValueFactory<Szczepienia, Time>("godzina"));
        peselPacSzczCol.setCellValueFactory(new PropertyValueFactory<Szczepienia,String>("peselPacjent"));
        nrPwzSzczCol.setCellValueFactory(new PropertyValueFactory<Szczepienia,Integer>("nrPwzLekarz"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Szczepienia,String>("status"));
        typSzczCol.setCellValueFactory(new PropertyValueFactory<Szczepienia,String>("idTyp"));
        this.tableSzczepienia.setItems(szczepienia);

    }

    /**
     * log out method,
     * changes scene to logWindow
     * @param event
     * @throws IOException
     */
    @FXML
    void wylogujClicked(ActionEvent event) throws IOException {
        btnPokazLekarzy.getScene().getWindow().hide();
        Parent root=  FXMLLoader.load(getClass().getResource("logwindow.fxml"));
        Stage primaryStage = new Stage();
        scene= new Scene(root,600,400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * initialization, connecting to database,
     * getting parameters from singleton
     */

    @FXML
    void initialize() {
        assert btnDodajLekarza != null : "fx:id=\"btnDodajLekarza\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert btnDodajTermin != null : "fx:id=\"btnDodajTermin\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert btnPokazLekarzy != null : "fx:id=\"btnPokazLekarzy\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert btnPokazTerminy != null : "fx:id=\"btnPokazTerminy\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert btnWyloguj != null : "fx:id=\"btnWyloguj\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert dataSzczCol != null : "fx:id=\"dataSzczCol\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert dodajGodzine != null : "fx:id=\"dodajGodzine\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert dtPickerDodajDate != null : "fx:id=\"dtPickerDodajDate\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert godzinaSzczCol != null : "fx:id=\"godzinaSzczCol\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert imieLekarzCol != null : "fx:id=\"imieLekarzCol\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert lekarzHaslo != null : "fx:id=\"lekarzHaslo\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert lekarzImie != null : "fx:id=\"lekarzImie\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert lekarzLogin != null : "fx:id=\"lekarzLogin\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert lekarzNazwisko != null : "fx:id=\"lekarzNazwisko\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert lekarzNrPwz != null : "fx:id=\"lekarzNrPwz\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert loginLekarzCol != null : "fx:id=\"loginLekarzCol\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert nazwiskoLekarzCol != null : "fx:id=\"nazwiskoLekarzCol\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert nrPwzCol != null : "fx:id=\"nrPwzCol\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert nrPwzSzczCol != null : "fx:id=\"nrPwzSzczCol\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert peselPacSzczCol != null : "fx:id=\"peselPacSzczCol\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert statusCol != null : "fx:id=\"statusCol\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert typSzczCol != null : "fx:id=\"typSzczCol\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert tableLekarz != null : "fx:id=\"tableLekarz\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert tableSzczepienia != null : "fx:id=\"tableSzczepienia\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert tvDodawanieLekarza != null : "fx:id=\"tvDodawanieLekarza\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert tvDodawanieTerminu != null : "fx:id=\"tvDodawanieTerminu\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert tfChorobaAdd != null : "fx:id=\"tfChorobaAdd\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert nrPwzLekarzaDodaj != null : "fx:id=\"nrPwzLekarzaDodaj\" was not injected: check your FXML file 'adminwindow.fxml'.";
        assert tfNazwaSzczepionki != null : "fx:id=\"tfNazwaSzczepionki\" was not injected: check your FXML file 'adminwindow.fxml'.";
        //databaseConnection = new DatabaseConnection("admin_punktu", "admin1");
        //databaseConnection.getConnection();
        userHolder = UserHolder.getInstance();
        System.out.println(UserHolder.getHaslo());
        String login = UserHolder.getLogin();
        String haslo = UserHolder.getHaslo();
        adminDAO = new AdminDAO(login,haslo);
        databaseConnection = adminDAO.getDatabaseConnection();
        databaseConnection.getConnection();

    }

}
