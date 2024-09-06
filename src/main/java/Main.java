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
            stage.setScene(scene);
            stage.setMinWidth(400);
            stage.setMinHeight(600);
            stage.setMaxHeight(600);
            stage.setMaxWidth(400);
            fxmlLoader.<MainWindow>getController().setNether(nether); // inject the Nether instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
