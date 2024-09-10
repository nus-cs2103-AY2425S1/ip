package kita;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Kita using FXML.
 */
public class Main extends Application {

    private Kita kita = new Kita();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setKita(kita);
            stage.setTitle("Kita Ikuyo");
            stage.setMaxWidth(420);
            stage.setMinWidth(420);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
