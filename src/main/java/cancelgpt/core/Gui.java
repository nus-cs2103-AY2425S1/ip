package cancelgpt.core;

import java.io.IOException;
import java.nio.file.Paths;

import cancelgpt.gui.GuiWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Test GUI.
 */
public class Gui extends Application {

    private CancelGpt cancelGpt;

    @Override
    public void start(Stage stage) {
        try {
            cancelGpt = new CancelGpt(Paths.get(System
                    .getProperty("user.home"), "accountexeregister-ip", "data"));
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/GuiWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<GuiWindow>getController().setCancelGpt(cancelGpt); // inject the CancelGpt instance
            stage.show();
        } catch (IOException e) {
            // Show error alert if CancelGpt fails to initialize
            showErrorAndExit(e.getMessage());
        }
    }

    /**
     * Displays an error alert and exits the application.
     *
     * @param message the message of the alert
     */
    private void showErrorAndExit(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

        // Exit the application
        Platform.exit();
        System.exit(1);
    }
}
