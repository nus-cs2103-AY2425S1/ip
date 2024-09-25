package bopes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class for launching the Bopes application.
 */
public class Main extends Application {

    private Bopes bopes = new Bopes();

    /**
     * Starts the JavaFX application and sets up the main window.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            scene.getStylesheets().add(Main.class.getResource("/view/style.css").toExternalForm());
            stage.setTitle("Bopes Task Manager");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBopes(bopes);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        launch(args); // This launches the JavaFX application
    }
}
