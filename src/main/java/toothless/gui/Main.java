package toothless.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 * This class is implemented by referencing to the JavaFX tutorial provided by the SE-EDU website.
 */
public class Main extends Application {

    private Toothless toothless = new Toothless();

    /**
     * Starts the GUI.
     *
     * @param stage the stage to start the GUI on
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setToothless(toothless);
            stage.setTitle("Toothless");
            stage.setMinHeight(220);
            stage.setMinWidth(400);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
