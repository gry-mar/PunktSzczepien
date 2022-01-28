import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PacjentWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text errorSapis;

    @FXML
    private TextField selectHour;

    @FXML
    private TextField selectHour1;

    @FXML
    private TextField selectHour2;

    @FXML
    private Button sortuj;

    @FXML
    private ChoiceBox<?> sortujPoList;

    @FXML
    private Button zapiszNaSzczepienie;

    @FXML
    void zapisNaSzczepienie(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert errorSapis != null : "fx:id=\"errorSapis\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert selectHour != null : "fx:id=\"selectHour\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert selectHour1 != null : "fx:id=\"selectHour1\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert selectHour2 != null : "fx:id=\"selectHour2\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert sortuj != null : "fx:id=\"sortuj\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert sortujPoList != null : "fx:id=\"sortujPoList\" was not injected: check your FXML file 'pacjentwindow.fxml'.";
        assert zapiszNaSzczepienie != null : "fx:id=\"zapiszNaSzczepienie\" was not injected: check your FXML file 'pacjentwindow.fxml'.";

    }

}
