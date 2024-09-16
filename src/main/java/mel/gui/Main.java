package mel.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mel.main.Mel;

/**
 * Main class to handle Mel's GUI.
 */
public class Main extends Application {
    private Mel mel = new Mel();

    /**
     * Runs GUI application for Mel.
     * @param stage primary stage provided by JavaFX.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setMel(mel);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
