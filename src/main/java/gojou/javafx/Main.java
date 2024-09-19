package gojou.javafx;

import java.io.IOException;

import gojou.Gojou;
import gojou.GojouException;
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

    private Gojou gojou = new Gojou("./data/Gojou.txt");

    private Image icon = new Image(this.getClass().getResourceAsStream("/images/Gojou4.png"));

    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

        try {
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Gojou");
            stage.getIcons().add(icon);
            fxmlLoader.<MainWindow>getController().setGojou(gojou); // inject the Gojou instance
            stage.show();
            this.gojou.loadTasksFromFile();
        } catch (IOException | GojouException e) {
            fxmlLoader.<MainWindow>getController().showErrorMsgOnStart(e);
            MainWindow.exitApplication(2);
        }
    }
}
