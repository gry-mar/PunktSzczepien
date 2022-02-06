import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    /**
     * main application
     * loads first, welcome window
     * @author Martyna Grygiel, Zofia Dobrowolska
     * @version 1.0
     * @since 25.01.2022
     */

    private static Scene scene;

    public MainApplication() {
    }

    /**
     * start app, opens first window
     * @param stage
     * @throws Exception
     */
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("welcomewindow"), 600, 400);
        stage.setScene(scene);
        stage.setTitle("Punkt szczepień");
        stage.show();
    }

    /**
     * setting root
     * @param fxml
     * @throws IOException
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * loads fxml
     * @param fxml String
     * @return
     * @throws IOException
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxml + ".fxml"));
        return (Parent)fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(new String[0]);
    }
}
