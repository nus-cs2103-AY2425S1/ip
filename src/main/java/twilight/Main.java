package twilight;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Twilight using FXML.
 */
public class Main extends Application {
    private Twilight twilight = new Twilight("./Twilight.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Twilight");
            fxmlLoader.<MainWindow>getController().setTwilight(twilight); //inject the Twilight instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
