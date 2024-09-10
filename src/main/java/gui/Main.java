package gui;

import jar.Jar;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Jar using FXML.
 */
public class Main extends Application {

    private Jar jar = new Jar("./data/jar.txt");

    /**
     * Starts the JavaFX application.
     * Sets up the primary stage and loads the FXML layout for the main window.
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
            fxmlLoader.<MainWindow>getController().setJar(jar);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
