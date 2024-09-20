package samson;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Samson samson = new Samson("data/samson.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Samson");

            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setMaxWidth(417);

            fxmlLoader.<MainWindow>getController().setSamson(samson);  // inject the samson instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

