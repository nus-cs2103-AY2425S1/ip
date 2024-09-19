package bob.gui;

import java.io.IOException;

import bob.Bob;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main entry point for the Bob chatbot GUI application using JavaFX and FXML.
 * This class sets up and launches the GUI for interacting with Bob.
 */
public class Main extends Application {

    private Bob bob = new Bob();

    /**
     * The main entry point for the JavaFX application.
     * Loads the FXML layout, sets up the scene, and displays the main window.
     *
     * @param stage The primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Bob");

            MainWindow controller = fxmlLoader.getController();
            controller.setBob(bob);
            controller.setStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

