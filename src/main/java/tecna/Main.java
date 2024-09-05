package tecna;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Tecna using FXML.
 */
public class Main extends Application {
    private Tecna tecna = new Tecna("data/tecna.json");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController().setTecna(tecna); // inject Duke instance
            fxmlLoader.<MainWindow>getController().setStage(stage);

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Tecna");
            stage.setResizable(false);

            tecna.greet();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
