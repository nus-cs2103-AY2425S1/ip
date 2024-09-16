import java.io.IOException;

import dave.Dave;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Dave using FXML.
 */
public class Main extends Application {

    private Dave dave = new Dave("C:\\Users\\thamy\\OneDrive\\data\\daveData.txt");

    /**
     * Starts the GUI application. This method is called by the JavaFX framework when
     * the application is launched.
     *
     * @param stage The primary stage for this application, onto which the application
     *              scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            assert dave != null : "Dave instance should be initialized"; // Ensure that dave is initialized

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            assert ap != null : "AnchorPane should have been loaded"; // Ensure that the layout was successfully loaded

            Scene scene = new Scene(ap);
            stage.setScene(scene);

            MainWindow controller = fxmlLoader.<MainWindow>getController();
            assert controller != null : "MainWindow controller should be initialized"; // Ensure controller is initialized

            controller.setDave(dave); // Inject the Dave instance into the controller
            controller.showWelcomeMessage(); // Show the welcome message

            stage.setMinHeight(220); // Set minimum height for the stage
            stage.setMinWidth(640);  // Set minimum width for the stage
            stage.show(); // Display the stage

        } catch (IOException e) {
            System.err.println("Failed to load FXML file: " + e.getMessage()); // Improved error handling
        }
    }
}
