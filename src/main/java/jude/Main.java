package jude;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jude.ui.MainWindow;

/**
 * A GUI for Jude using FXML.
 */
public class Main extends Application {
    private static final String DEFAULT_FILE_PATH = "data/jude.txt";

    private Jude jude = new Jude(DEFAULT_FILE_PATH);

    /**
     * Starts the application by setting necessary nodes of the stage.
     * <p>
     * Load the main window and set the scene, Jude and set stage to the main window.
     * Displays the stage and generate a welcome message.
     * </p>
     * @param stage The stage which is to be shown to the user.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Load the default screen
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load(); // initialises the default screen and its controller by FXML loader

            // Set the scene from the default screen
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Set Jude instance from the controller
            MainWindow controller = fxmlLoader.getController();

            // Assert jude should not be null
            assert jude != null : "jude should not be null";

            controller.setJude(jude);

            // Set stage from the controller
            controller.setStage(stage);

            stage.show();

            controller.displayWelcomeMessage();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
