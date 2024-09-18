package meowmeow;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for MeowMeow using FXML.
 */
public class Main extends Application {

    private MeowMeow meowmeow = new MeowMeow("./data/meowmeow.txt");

    /**
     * Starts the application by setting up the main window and scene.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMeowMeow(meowmeow);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
