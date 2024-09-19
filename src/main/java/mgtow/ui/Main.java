package mgtow.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mgtow.Mgtow;

import java.io.IOException;

/**
 * A GUI for Mgtow using FXML.
 * This class is responsible for launching the JavaFX application and setting up the main window.
 */
public class Main extends Application {

    /** The main Mgtow instance for the application. */
    private Mgtow mgtow = new Mgtow("./data/MGTOW.txt");

    /**
     * Starts the JavaFX application.
     * This method sets up the main window, loads the FXML, and injects the Mgtow instance into the controller.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("MGTOW");
            fxmlLoader.<MainWindow>getController().setMgtow(mgtow);  // inject the Mgtow instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main entry point for the JavaFX application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}