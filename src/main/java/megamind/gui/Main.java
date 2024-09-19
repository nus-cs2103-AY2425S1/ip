package megamind.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import megamind.main.Megamind;

/**
 * The `Main` class serves as the entry point for the Megamind GUI application.
 * It extends `Application` and is responsible for initializing and displaying
 * the main window of the application.
 */
public class Main extends Application {
    private final Megamind megamind = new Megamind();

    /**
     * Starts the JavaFX application by setting up the main stage and loading
     * the FXML layout for the main window.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Megamind");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "FXMLLoader should successfully load the FXML file";
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMegamind(megamind);
            stage.setOnCloseRequest(event -> handleCloseRequest());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the close request for the application by calling the `exit` method
     * of the `Megamind` instance.
     */
    private void handleCloseRequest() {
        Megamind.exit();
    }
}
