package neuro.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import neuro.Neuro;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Neuro neuro = new Neuro("data/Neuro.txt");

    @Override
    public void start(Stage stage) {
        assert stage != null : "Stage object should not be null";

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setNeuro(neuro); // inject the Neuro instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
