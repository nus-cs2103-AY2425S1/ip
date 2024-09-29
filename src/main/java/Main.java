import java.io.IOException;

import controller.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("BotManager");
            stage.getIcons().add(new Image("/images/BotManager.jpg"));
            stage.setScene(scene);
            stage.setMinHeight(800);
            stage.setMinWidth(1200);
            fxmlLoader.<MainWindow>getController().setup(); // inject the BotManager instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

