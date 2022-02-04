import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnGoToLog;

    private Stage stage;
    private Scene scene;

    @FXML
    void goToLog(ActionEvent event){
       try{ Parent root=  FXMLLoader.load(getClass().getResource("logwindow.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root,800,600);
        stage.setScene(scene);
        stage.show();

    } catch (Exception e) {
        e.printStackTrace();
        e.getCause();
    }

    }

    @FXML
    void initialize() {
        assert btnGoToLog != null : "fx:id=\"btnGoToLog\" was not injected: check your FXML file 'welcomewindow.fxml'.";

    }

}

