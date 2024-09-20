package shrimp.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import shrimp.Shrimp;

/**
 * The Main class serves as the entry point for the JavaFX GUI application.
 * It is responsible for loading the main application window and setting up the GUI.
 */
public class Main extends Application {
    private final Shrimp shrimp = new Shrimp();

    /**
     * This method is called when the application is launched.
     * It sets up the main application window by loading the FXML layout,
     * configuring the scene, and displaying the stage.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.setTitle("Shrimp");
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/shrimp.jpeg")));
            fxmlLoader.<MainWindow>getController().setShrimp(shrimp); // inject the Shrimp instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called when the application is closed.
     * It ensures that tasks are saved before the application exits.
     */
    @Override
    public void stop() {
        shrimp.saveTasks();
    }
}
