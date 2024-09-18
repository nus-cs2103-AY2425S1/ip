package seedu.maxine;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Maxine using FXML.
 */
public class Main extends Application {

    private Maxine maxine = new Maxine();

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("MaxineBot");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMaxine(maxine);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
