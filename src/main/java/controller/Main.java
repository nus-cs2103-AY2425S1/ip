package controller;

import java.io.IOException;

import blitz.Blitz;
import exception.BlitzException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents the GUI for Blitz application using FXML.
 */
public class Main extends Application {
    private Blitz blitz;

    /**
     * Initializes the Blitz JavaFX application.
     *
     * @param stage Primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            blitz = new Blitz("./Blitz.txt");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Blitz");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBlitz(blitz);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BlitzException e2) {
            System.exit(1);
        }
    }
}
