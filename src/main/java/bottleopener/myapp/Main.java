package bottleopener.myapp;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main application class for the BottleOpener chatbot application.
 *
 * This class serves as the entry point for the JavaFX application.
 * It sets up the primary stage and loads the main user interface from an FXML file.
 */
public class Main extends Application {

    private BottleOpener bot = new BottleOpener();

    /**
     * Initializes and shows the primary stage for the BottleOpener application.
     *
     * This method loads the FXML layout for the main window, sets up the scene with the loaded layout,
     * and initializes the `MainWindow` controller with the `BottleOpener` instance and the primary stage.
     *
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow controller = fxmlLoader.<MainWindow>getController();
            controller.setBottleOpener(bot);
            controller.setStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
