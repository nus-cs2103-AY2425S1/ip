package dude.gui;

import java.io.IOException;

import dude.Dude;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Dude using FXML.
 */
public class Main extends Application {
    private static final String FILE_PATH = "./data";
    private Dude dude;

    public Main() {
        dude = new Dude(FILE_PATH);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Dude");
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setDude(dude); // inject the Dude instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
