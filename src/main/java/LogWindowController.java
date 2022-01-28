import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
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
    private TextField pacjentHaslo;

    @FXML
    private TextField pacjentLogin;

    @FXML
    private Text tvLogInfo;


    @FXML
        void clickedUtworzKonto(ActionEvent event) {

        }

        @FXML
        void clickedZaloguj(ActionEvent event) {

            if (!this.login.getText().equals((Object)null)) {
                tvLogInfo.setText("Probowales sie zalogowac");
            }
//            if(!login.getText().isBlank() && !haslo.getText().isBlank()){
//
//
//            }else{
//                tvLogInfo.setText("Wprowadz login i haslo");
//            }

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
            assert pacjentHaslo != null : "fx:id=\"pacjentHaslo\" was not injected: check your FXML file 'logwindow.fxml'.";
            assert pacjentLogin != null : "fx:id=\"pacjentLogin\" was not injected: check your FXML file 'logwindow.fxml'.";
            assert tvLogInfo != null : "fx:id=\"tvLogInfo\" was not injected: check your FXML file 'logwindow.fxml'.";
        }

        public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String verifyLogin = "procedura logowanie";
        }

    }
