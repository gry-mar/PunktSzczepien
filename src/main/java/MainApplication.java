import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {


    private static Scene scene;

    public MainApplication() {
    }

    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("welcomewindow"), 600, 400);
        stage.setScene(scene);
        stage.setTitle("Punkt szczepień");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxml + ".fxml"));
        return (Parent)fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(new String[0]);
    }
}
