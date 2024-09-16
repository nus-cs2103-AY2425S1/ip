package peter;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Peter using FXML.
 */
public class Main extends Application {
    private Peter peter = new Peter();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("PETER");
            stage.getIcons().add(new Image("/images/chatbot.png"));
            fxmlLoader.<MainWindow>getController().setPeter(peter);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}