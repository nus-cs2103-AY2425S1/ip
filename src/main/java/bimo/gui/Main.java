package bimo.gui;

import java.io.IOException;

import bimo.Bimo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Bimo bimo = new Bimo("data/Bimo.txt");

    @Override
    public void start(Stage stage) {
        try {
            System.out.println(System.getProperty("user.dir"));
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBimo(bimo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
