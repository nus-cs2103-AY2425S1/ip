package bestie;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for bestie using FXML.
 */
public class Main extends Application {

    private Bestie bestie = new Bestie("bestie.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            assert fxmlLoader.getLocation() != null: "FXML file could not be found";

            AnchorPane ap = fxmlLoader.load();
            assert ap != null: "Loaded AnchorPane is null";

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            // inject the Bestie instance
            fxmlLoader.<MainWindow>getController().setBestie(bestie);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}