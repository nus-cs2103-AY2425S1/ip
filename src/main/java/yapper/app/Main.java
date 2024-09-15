package yapper.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import yapper.components.Yapper;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Yapper yapper = new Yapper("./YappingData/YappingSession.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/icon.png")));
            stage.setTitle("Yapper");
            // Inject Yapper instance
            fxmlLoader.<MainWindow>getController().setYapper(yapper);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
