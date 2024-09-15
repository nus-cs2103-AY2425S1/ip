package papadom;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PapadomApp extends Application {
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 400;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file that describes the UI layout
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));

            // Create a scene using the loaded FXML, setting the window size (600x400)
            Scene scene = new Scene(fxmlLoader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);

            // Set the title of the window
            primaryStage.setTitle("Papadom Chatbot");

            // Set the scene (i.e., the window content) and display the window
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            Logger.getLogger(PapadomApp.class.getName()).log(Level.SEVERE, "Failed to load FXML", e);
        }
    }

    // The main method launches the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }
}
