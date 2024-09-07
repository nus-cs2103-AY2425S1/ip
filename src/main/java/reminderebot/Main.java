package reminderebot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import reminderebot.controls.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Reminderebot reminderebot = new Reminderebot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setReminderebot(reminderebot); // inject the Reminderebot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
