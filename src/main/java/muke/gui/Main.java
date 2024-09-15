package muke.gui;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import muke.Muke;

/**
 * Main entry point for the application into the GUI
 *
 */
public class Main extends Application {
    private Muke muke = new Muke();

    @Override
    public void start(Stage stage) {
        try {
            URL fxmlLocation = Main.class.getResource("/view/MainWindow.fxml");
            if (fxmlLocation == null) {
                System.err.println("FXML file not found at /view/MainWindow.fxml");
            } else {
                System.out.println("FXML file found: " + fxmlLocation.toExternalForm());
            }
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMuke(muke);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            fxmlLoader.<MainWindow>getController().sendIntro();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}