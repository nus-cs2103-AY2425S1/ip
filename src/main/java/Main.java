import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vinegar.Vinegar;

/**
 * A GUI for Vinegar using FXML.
 */
public class Main extends Application {


    private Vinegar vinegar = new Vinegar();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Vinegar");
            fxmlLoader.<MainWindow>getController().setVinegar(vinegar);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}