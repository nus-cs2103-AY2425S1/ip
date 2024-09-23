import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import xbot.XBot;

/**
 * A GUI for XBot using FXML.
 */
public class Main extends Application {

    private XBot xbot = new XBot();
    private Image icon = new Image(this.getClass().getResourceAsStream("/images/Flower.jpeg"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("XBot");
            stage.getIcons().add(icon);
            fxmlLoader.<MainWindow>getController().setXBot(xbot); // inject the XBot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
