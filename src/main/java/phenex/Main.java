package phenex;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Phenex using FXML.
 */
public class Main extends Application {

    private Phenex phenex = new Phenex(Phenex.DEFAULT_FILEPATH);

    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML layout for the MainWindow
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // Create and set the scene
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Phenex");

            // Inject the Phenex instance into the controller
            MainWindow controller = fxmlLoader.getController();
            controller.setPhenex(phenex);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
