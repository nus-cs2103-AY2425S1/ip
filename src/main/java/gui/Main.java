package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import papagu.ui.Papagu;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Papagu papagu = new Papagu("src/main/java/data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPapagu(papagu);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

