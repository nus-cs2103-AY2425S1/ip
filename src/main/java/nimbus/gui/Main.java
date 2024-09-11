package nimbus.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nimbus.Nimbus;

/**
 * Main file to kickstart gui
 */
public class Main extends Application {

    private Nimbus nimbus = new Nimbus("nimbus.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Nimbus");
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setNimbus(nimbus);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
