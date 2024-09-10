package jay.gui;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jay.Jay;

/**
 * A GUI for Duke using FXML.
 */
public class Gui extends Application {

    private final Jay jay = new Jay("Jay");

    @Override
    public void start(Stage stage) {
        try {
            URL fxmlResource = Gui.class.getResource("/view/DialogBox.fxml");
            assert fxmlResource != null : "FXML resource not found";

            FXMLLoader fxmlLoader = new FXMLLoader(fxmlResource);
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJay(jay); // inject the Jay instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

