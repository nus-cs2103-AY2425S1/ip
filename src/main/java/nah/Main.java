package nah;

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

    private Nah nah = new Nah();

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow(nah);
        mainWindow.setStage(stage);
    }
}
