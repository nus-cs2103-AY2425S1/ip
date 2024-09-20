package nen;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nen.components.MainWindow;
import nen.nen2.Nen2;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Nen2 nen2 = new Nen2("data/nen2.txt");

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Nen2");
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setNen2(nen2);
            fxmlLoader.<MainWindow>getController().greet();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
