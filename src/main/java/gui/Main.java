package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mizz.Mizz;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Mizz mizz = new Mizz("Hello! I'm Mizz\nWhat can I do for you?", "./store/storage.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/css/main.css").toExternalForm());
            scene.getStylesheets()
                    .add(getClass().getResource("/css/dialog-box.css").toExternalForm());
            stage.setMinWidth(417);
            stage.setMinHeight(617);
            fxmlLoader.<MainWindow>getController().setMizz(this.mizz); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
