package meeju;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Meeju using FXML.
 */
public class Main extends Application {

    private Meeju meeju = new Meeju();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Meeju");
            stage.setMinHeight(630);
            stage.setMinWidth(600);
            fxmlLoader.<MainWindow>getController().setMeeju(meeju);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
