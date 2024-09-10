package gui;

import java.io.IOException;

import gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pixy.Pixy;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Pixy pixy = new Pixy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setPixy(pixy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
