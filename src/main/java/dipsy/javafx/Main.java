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
        assert stage != null : "Stage should not be null";

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "AnchorPane should not be null after loading FXML";

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);

            assert dipsy != null : "Dipsy instance should not be null";
            fxmlLoader.<MainWindow>getController().setDipsy(dipsy); // inject the Dipsy instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
