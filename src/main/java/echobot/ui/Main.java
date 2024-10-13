package echobot.ui;

import echobot.EchoBot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private final EchoBot echoBot = new EchoBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Echobot");
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/echobot.png")));
            fxmlLoader.<MainWindow>getController().setEchobot(echoBot);  // inject the echobot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
