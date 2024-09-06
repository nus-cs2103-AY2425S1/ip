package papadom;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PapadomApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file that describes the UI layout
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));

            // Create a scene using the loaded FXML, setting the window size (600x400)
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);

            // Set the title of the window
            primaryStage.setTitle("Papadom Chatbot");

            // Set the scene (i.e., the window content) and display the window
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // The main method launches the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }

    /*
    To run:
    ./gradlew clean build
    ./gradlew run
     */
}
