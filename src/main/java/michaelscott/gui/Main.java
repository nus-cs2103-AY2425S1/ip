package michaelscott.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import michaelscott.MichaelScott;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final MichaelScott michaelScott = new MichaelScott();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            assert fxmlLoader != null : "fxmlLoader wasn't loaded properly in Main.java";
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(616);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setMichaelScott(michaelScott);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
