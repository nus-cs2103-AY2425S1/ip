package wansbot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import wansbot.ui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private WansBot wansBot = new WansBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            Image icon = new Image(this.getClass().getResourceAsStream("/images/daWansBot.png"));
            stage.setScene(scene);
            stage.setTitle("WansBot");
            stage.getIcons().add(icon);
            fxmlLoader.<MainWindow>getController().setWansBot(wansBot);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
