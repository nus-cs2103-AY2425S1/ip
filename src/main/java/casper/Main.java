package casper;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main entry point for the Casper application.
 * This class extends {@link Application} and sets up the primary stage for the application.
 * It loads the FXML layout for the main window, sets up the scene, and initializes the controller
 * with an instance of {@link Casper}.
 */
public class Main extends Application {
    private Casper casper = new Casper();

    /**
     * Starts the application by setting up the primary stage.
     * This method loads the FXML layout for the main window, creates a scene with it,
     * and sets it on the primary stage. It also initializes the {@link MainWindow} controller
     * with the {@link Casper} instance and adds a start-up message.
     *
     * @param stage The primary stage for this application, onto which the application scene is set.
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Casper Chat");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCasper(casper);
            fxmlLoader.<MainWindow>getController().addStartUpMessage();
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
