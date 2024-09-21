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

    private Naega naega = new Naega("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            System.out.println("Loading FXML...");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            System.out.println("FXML Loaded!");
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setNaega(naega);
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
