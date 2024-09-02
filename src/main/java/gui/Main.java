package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import regina.Regina;

import java.io.IOException;

/**
 * A GUI application for the Regina chatbot using FXML.
 * This class initializes the JavaFX application and sets up the main user interface.
 */
public class Main extends Application {

    private final Regina regina = new Regina(); // Initialize the Regina instance

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Regina Chatbot"); // Set a title for the window

            // Get the controller for the main window
            MainWindow mainWindowController = fxmlLoader.getController();
            mainWindowController.setRegina(regina); // Set the Regina instance
            regina.setMainWindow(mainWindowController);
            mainWindowController.initialize();

            stage.show(); // Show the main application window
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
