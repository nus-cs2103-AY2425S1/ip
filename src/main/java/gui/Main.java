package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import dude.Dude;

/**
 * A GUI for Dude using FXML.
 */
public class Main extends Application {

    private Dude dude = new Dude();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDude(dude);  // inject the Dude instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
