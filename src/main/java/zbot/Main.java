package zbot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import zbot.ui.MainWindow;

/**
 * A GUI for ZBot using FXML.
 */
public class Main extends Application {
    private ZBot zbot = new ZBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setZbot(zbot); // inject the ZBot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
