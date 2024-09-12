package nave;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Nave using FXML.
 */
public class Main extends Application {

    private final Nave nave = new Nave();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setNave(nave); // inject the Nave instance
            fxmlLoader.<MainWindow>getController().setScene(scene);
            fxmlLoader.<MainWindow>getController().greetUser();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
