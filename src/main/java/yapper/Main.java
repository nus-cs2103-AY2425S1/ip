package yapper;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class that serves as the entry point for the Yapper application.
 * This class extends {@code Application} and initializes the GUI by loading the FXML layout.
 */
public class Main extends Application {
    private final Yapper yapper = new Yapper("data/tasks.txt");

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    /**
     * Starts the JavaFX application by loading the main window from FXML and setting the scene.
     *
     * @param stage the primary stage for this application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Yapper Chatbot");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setYapper(yapper);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
