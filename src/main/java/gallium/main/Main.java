package gallium.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Gallium using FXML.
 */
public class Main extends Application {

    private Gallium gallium = new Gallium("./data/gallium.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setGallium(gallium);  // inject the Gallium instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
