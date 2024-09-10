package joe.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import joe.main.Joe;

/**
 * A GUI for Joe using FXML.
 */
public class Main extends Application {

    private final Joe joe = new Joe("data/joe.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJoe(joe);  // inject the Joe instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


