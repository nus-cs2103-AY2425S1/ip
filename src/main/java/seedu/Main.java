package seedu;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import seedu.ui.MainWindow;

/**
 * A GUI for Bob using FXML.
 */
public class Main extends Application {

    private Bob bob = new Bob();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Bob Task Tracker");
            fxmlLoader.<MainWindow>getController().setBob(bob); // inject the Bob instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        bob.exit();
    }
}
