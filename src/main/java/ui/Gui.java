package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import levelhundred.LevelHundred;

/**
 * A GUI for Duke using FXML.
 */
public class Gui extends Application {

    private LevelHundred levelHundred = new LevelHundred();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setLevelHundred(this.levelHundred); // inject LevelHundred
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
