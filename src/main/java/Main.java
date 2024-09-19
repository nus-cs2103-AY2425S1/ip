import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nether.Nether;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private final Nether nether = new Nether("./data/nether.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setNether(nether); // inject the Nether instance
            stage.setTitle("Nether");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
