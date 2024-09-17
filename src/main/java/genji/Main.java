package genji;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Genji using FXML.
 */
public class Main extends Application {
    private Genji genji = new Genji();

    /**
     * Starts the program
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader
                    (Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            assert stage != null : "Stage not set successfully";
            fxmlLoader.<MainWindow>getController().setGenji(genji);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}