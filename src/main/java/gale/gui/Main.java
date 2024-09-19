package gale.gui;

import java.io.IOException;

import gale.Gale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Gale using JavaFX.
 */
public class Main extends Application {
    private Gale gale = new Gale();

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Gale — The Windy Task Manager");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setGale(gale);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
