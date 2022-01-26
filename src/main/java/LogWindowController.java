import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;


public class LogWindowController {

        @FXML // ResourceBundle that was given to the FXMLLoader
        private ResourceBundle resources;

        @FXML // URL location of the FXML file that was given to the FXMLLoader
        private URL location;

        @FXML // fx:id="checkboxAdmin"
        private CheckBox checkboxAdmin; // Value injected by FXMLLoader

        @FXML // fx:id="haslo"
        private TextField haslo; // Value injected by FXMLLoader

        @FXML // fx:id="imie"
        private TextField imie; // Value injected by FXMLLoader

        @FXML // fx:id="login"
        private TextField login; // Value injected by FXMLLoader

        @FXML // fx:id="nazwisko"
        private TextField nazwisko; // Value injected by FXMLLoader

        @FXML // fx:id="pesel"
        private TextField pesel; // Value injected by FXMLLoader

        @FXML // fx:id="telefon"
        private TextField telefon; // Value injected by FXMLLoader

        @FXML // fx:id="utworzKonto"
        private Button utworzKonto; // Value injected by FXMLLoader

        @FXML // fx:id="zaloguj"
        private Button zaloguj; // Value injected by FXMLLoader

        @FXML
        void clickedUtworzKonto(ActionEvent event) {

        }

        @FXML
        void clickedZaloguj(ActionEvent event) {

        }

        @FXML
        void isAdmin(ActionEvent event) {

        }

        @FXML // This method is called by the FXMLLoader when initialization is complete
        void initialize() {
            assert checkboxAdmin != null : "fx:id=\"checkboxAdmin\" was not injected: check your FXML file 'logwindow.fxml'.";
            assert haslo != null : "fx:id=\"haslo\" was not injected: check your FXML file 'logwindow.fxml'.";
            assert imie != null : "fx:id=\"imie\" was not injected: check your FXML file 'logwindow.fxml'.";
            assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'logwindow.fxml'.";
            assert nazwisko != null : "fx:id=\"nazwisko\" was not injected: check your FXML file 'logwindow.fxml'.";
            assert pesel != null : "fx:id=\"pesel\" was not injected: check your FXML file 'logwindow.fxml'.";
            assert telefon != null : "fx:id=\"telefon\" was not injected: check your FXML file 'logwindow.fxml'.";
            assert utworzKonto != null : "fx:id=\"utworzKonto\" was not injected: check your FXML file 'logwindow.fxml'.";
            assert zaloguj != null : "fx:id=\"zaloguj\" was not injected: check your FXML file 'logwindow.fxml'.";

        }

    }
