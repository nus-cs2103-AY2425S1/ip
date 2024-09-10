package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import loafy.main.Loafy;


/**
 * A GUI for Loafy using FXML.
 */
public class Main extends Application {

    private Loafy loafy = new Loafy("./data/loafy.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLoafy(loafy);  // inject the Loafy instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}