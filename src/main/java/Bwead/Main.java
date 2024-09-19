package Bwead;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class of the program.
 */
public class Main extends Application {
    private Bwead bwead = new Bwead("./src/main/java/Bwead/historyFile");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Bwead");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBwead(bwead);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

