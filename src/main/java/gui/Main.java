package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import regina.Regina;

/**
 * A GUI application for the Regina chatbot using FXML.
 * This class initializes the JavaFX application and sets up the main user interface.
 */
public class Main extends Application {

    private final Regina regina = new Regina(); // Initialize the Regina instance

    /**
     * The main entry point for the JavaFX application.
     * This method is called when the application is launched.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CheckboxWindow.fxml"));

            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Regina Chatbot"); // Set a title for the window

            // Get the controller for the main window
            MainWindow mainWindowController = fxmlLoader.getController();
            mainWindowController.setRegina(regina); // Set the Regina instance

            stage.show(); // Show the main application window

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method to launch the application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Method to open the Checkbox window and populate it with the list of tasks.
     */
    public void openCheckboxWindow() {
        try {
            // Load the checkbox window FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CheckboxWindow.fxml"));
            AnchorPane root = loader.load();

            // Set up the checkbox controller
            CheckboxController checkboxController = loader.getController();
            checkboxController.setRegina(regina); // Pass the Regina instance

            // Populate the checkboxes with tasks from Regina
            checkboxController.createCheckboxesFromTaskList(regina.getListOfTasks());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
