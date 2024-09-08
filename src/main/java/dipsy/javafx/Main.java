package dipsy.javafx;

import java.io.IOException;

import dipsy.Dipsy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Dipsy using FXML.
 */
public class Main extends Application {

    private Dipsy dipsy = new Dipsy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDipsy(dipsy); // inject the Dipsy instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
