package elsa.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import elsa.ElsaException;
import elsa.Storage;
import elsa.ui.Elsa;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Elsa using FXML.
 */
public class Gui extends Application {
    // Creates a logger for this class
    private static final Logger logger = Logger.getLogger(Gui.class.getName());

    private final Elsa elsa = new Elsa();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/GuiWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Get the controller and set Elsa and the stage
            GuiWindow controller = fxmlLoader.getController();
            controller.setElsa(elsa);
            controller.setStage(stage); // Pass the stage to the controller

            stage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load the FXML file for the GUI", e);
        }

        // Save all tasks to the Elsa.txt data file when the window is closed
        stage.setOnCloseRequest(event -> {
            try {
                Storage storage = new Storage();
                storage.saveTasksToDataFile(elsa.getTasks());
            } catch (ElsaException e) {
                logger.log(Level.SEVERE, "Error occurred while saving tasks when closing the window", e);
            }
        });
    }
}
