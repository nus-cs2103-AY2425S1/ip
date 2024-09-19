package atreides;

import java.io.IOException;

import atreides.ui.Atreides;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Atreides atreides = new Atreides("src/main/atreides.ui.Atreides.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAtreides(atreides); // inject the Atreides instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
