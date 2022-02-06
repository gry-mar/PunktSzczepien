import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class LogWindowController {
    /**
     * class to interact with login wiew
     * @author Martyna Grygiel, Zofia Dobrowolska
     * @version 1.0
     * @since 04.02.2022
     */

    private DatabaseConnection databaseConnection;

    private Stage stage;
    private Scene scene;


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;


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

    private List<String> logHasloRola = new ArrayList<>();
    private UserHolder userHolder = new UserHolder(null, null);

    /**
     * method to create account
     * calls sql procedure
     *
     * @param event
     * @throws SQLException
     */

    @FXML
    void clickedUtworzKonto(ActionEvent event) throws SQLException {
        try {
            String login = pacjentLogin.getText().toString();
            String haslo = pacjentHaslo.getText().toString();
            String peselPac = pesel.getText().toString();
            String imiePac = imie.getText().toString();
            String nazwiskoPac = nazwisko.getText().toString();
            String telefonPac = telefon.getText().toString();
            CallableStatement cStm = databaseConnection.getDatabaseLink().prepareCall("{call rejestracja(?,?,?,?,?,?,?)}");
            cStm.setString(1, login);
            cStm.setString(2, haslo);
            cStm.setString(3, peselPac);
            cStm.setString(4, imiePac);
            cStm.setString(5, nazwiskoPac);
            cStm.setString(6, telefonPac);
            cStm.registerOutParameter(7, Types.BIT);
            cStm.executeUpdate();
            boolean czyIstnieje = (Boolean) (cStm.getBoolean(7));
            if (czyIstnieje) {
                tvLogInfo.setText("Uworzono konto, teraz mozesz sie zalogowac");
            } else {
                tvLogInfo.setText("Takie konto istnieje, zaloguj sie");
            }
        } catch (NullPointerException e) {
            tvLogInfo.setText("Wprowadź wszystkie dane logowania");
        }
    }

    /**
     * method to change application scene
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    void clickedZaloguj(ActionEvent event) throws SQLException {
        boolean czyPoprawne = validateLogin();
        if (!this.login.getText().isEmpty()) {
            tvLogInfo.setText("Probowales sie zalogowac");

            if (czyPoprawne) {
                try {
                    userHolder = new UserHolder(login.getText(), haslo.getText());
                    if (logHasloRola.get(0).equals("pacjent")) {
                        Node node = (Node) event.getSource();
                        stage = (Stage) (node.getScene().getWindow());
                        Parent root = FXMLLoader.load(getClass().getResource("pacjentwindow.fxml"));
                        scene = new Scene(root, 1000, 700);
                        stage.setScene(scene);
                        stage.show();
                    } else if (logHasloRola.get(0).equals("admin_punktu")) {
                        Node node = (Node) event.getSource();
                        stage = (Stage) (node.getScene().getWindow());
                        Parent root = FXMLLoader.load(getClass().getResource("adminwindow.fxml"));
                        scene = new Scene(root, 900, 600);
                        stage.setScene(scene);
                        stage.show();
                    } else if (logHasloRola.get(0).equals("lekarz")) {
                        Node node = (Node) event.getSource();
                        stage = (Stage) (node.getScene().getWindow());
                        Parent root = FXMLLoader.load(getClass().getResource("lekarzwindow.fxml"));
                        scene = new Scene(root, 1000, 700);
                        stage.setScene(scene);
                        stage.show();

                    } else {
                        tvLogInfo.setText("Wprowadzono niepoprawne dane logowania");
                    }
                    login.clear();
                    haslo.clear();

                } catch (Exception e) {
                    e.printStackTrace();
                    e.getCause();
                }
            }
        } else {
            tvLogInfo.setText("Wprowadz login i haslo");
        }

    }


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
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
        databaseConnection = new DatabaseConnection("admin_punktu", "admin1");
        databaseConnection.getConnection();

    }

    /**
     * method that calls function logowanie and validates login and password
     *
     * @return boolean if user exists
     * @throws SQLException
     */
    public boolean validateLogin() throws SQLException {
        String loginOut;
        String hasloOut;
        String peselOut = null;
        int nrPwzOut = 0;
        String logIHaslo;
        boolean czyIstnieje = false;
        if (login.getText().toString().equals("admin_punktu") && haslo.getText().toString().equals("admin1")) {
            loginOut = "admin_punktu";
            hasloOut = "admin1";
            logHasloRola.add(loginOut);
            logHasloRola.add(hasloOut);
            logHasloRola.add("A");
            czyIstnieje = true;
        } else {

            CallableStatement cstm = databaseConnection.getDatabaseLink().prepareCall("{?=call logowanie(?,?)}");
            cstm.registerOutParameter(1, Types.VARCHAR);
            cstm.setString(2, login.getText().toString());
            cstm.setString(3, haslo.getText().toString());
            cstm.execute();
            logIHaslo = cstm.getString(1);
            if (logIHaslo == null) {
                czyIstnieje = false;
            } else {
                logHasloRola = Arrays.asList(logIHaslo.split(","));
                czyIstnieje = true;
            }
        }
        return czyIstnieje;
    }


}
