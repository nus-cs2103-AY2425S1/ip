package nimbus.gui;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nimbus.Nimbus;

/**
 * Main file to kickstart gui
 */
public class Main extends Application {

    private Nimbus nimbus;
    private Image nimbusImage =
            new Image(this.getClass().getResourceAsStream("/images/nimbus.jpg"));

    @Override
    public void start(Stage stage) {
        nimbus = new Nimbus("nimbus.txt");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Nimbus");
            stage.getIcons().add(nimbusImage);

            stage.setMinHeight(220);
            stage.setMinWidth(860);

            fxmlLoader.<MainWindow>getController().setNimbus(nimbus);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
