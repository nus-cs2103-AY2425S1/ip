package nerf.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nerf.Nerf;

/**
 * Main GUI class
 */
public class Main extends Application {

    private Nerf nerf = new Nerf("data/taskStorage.txt");
    /**
     * Initialise and run the GUI scene.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Nerf chatbot");
            fxmlLoader.<MainWindow>getController().setNerf(nerf);  // inject the Nerf instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
