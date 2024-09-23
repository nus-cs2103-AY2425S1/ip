package maga.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import maga.Maga;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Maga maga = new Maga();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Maga");
            fxmlLoader.<MainWindow>getController().setMaga(maga);  // inject the Maga instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
