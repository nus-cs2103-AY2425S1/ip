package bellroy.GUI;

import java.io.IOException;

import bellroy.Bellroy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Bellroy using FXML.
 */
public class Main extends Application {

    private Bellroy bellroy = new Bellroy("Bellroy.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("bellroy");
            fxmlLoader.<MainWindow>getController().setBellroy(bellroy);  // inject the Bellroy instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
