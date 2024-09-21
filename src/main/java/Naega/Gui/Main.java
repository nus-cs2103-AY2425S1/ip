package Naega.Gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import Naega.*;

/**
 * A GUI for Naega using FXML.
 */
public class Main extends Application {

    private Naega naega;

    @Override
    public void start(Stage stage) {
        try {
            System.out.println("Loading FXML...");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            System.out.println("FXML Loaded!");

            // Initialize the Naega instance with a file path
            naega = new Naega("data/tasks.txt");

            // If it's the first run, load sample data
            if (naega.isFirstRun()) {
                naega.loadSampleData();  // Load sample data during the first run
            }

            // Setup scene and show the stage
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Set the Naega instance in the MainWindow controller
            MainWindow controller = fxmlLoader.getController();
            controller.setNaega(naega);  // Inject the Naega instance into MainWindow

            stage.show();
            System.out.println("Stage shown!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}