package dumpling.ui;

import java.io.IOException;

import dumpling.Dumpling;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 * Obtained from JavaFx tutorial.
 */
public class Main extends Application {

    private Dumpling dumpling;

    public Main() {
        this.dumpling = new Dumpling();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDumpling(dumpling);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
