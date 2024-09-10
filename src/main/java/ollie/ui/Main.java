package ollie.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ollie.Ollie;

/**
 * A GUI for Ollie using FXML.
 */
public class Main extends Application {

    private Ollie ollie = new Ollie();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            assert fxmlLoader != null : "Oops! FXMLLoader failed to load MainWindow.fxml";
            stage.setTitle("Ollie");
            fxmlLoader.<MainWindow>getController().setOllie(ollie);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
