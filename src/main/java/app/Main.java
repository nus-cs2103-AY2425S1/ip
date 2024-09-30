package app;

import java.io.IOException;

import deez.Deez;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Deez using FXML.
 */
public class Main extends Application {

    private Deez deez = new Deez();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Deez");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDeez(deez); //inject Deez
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
