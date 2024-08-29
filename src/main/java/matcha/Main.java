package matcha;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * GUI for Matcha.
 */
public class Main extends Application {
    private Matcha matcha = new Matcha();
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Matcha");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMatcha(matcha);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
