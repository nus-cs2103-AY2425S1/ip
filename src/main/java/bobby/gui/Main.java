package bobby.gui;

import java.io.IOException;

import bobby.Bobby;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class for Bobby GUI
 */
public class Main extends Application {

    private static String filePath = "data.txt";

    private Bobby bobby = new Bobby(filePath);
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Bobby Chatbot");

            Image icon = new Image(getClass().getResourceAsStream("/images/butlerIcon.png"));
            stage.getIcons().add(icon);

            fxmlLoader.<MainWindow>getController().setBobby(bobby);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
