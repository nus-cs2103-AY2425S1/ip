package stobberi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Stobberi stobberi = new Stobberi();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            Image icon = new Image(this.getClass().getResourceAsStream("/images/Stobberi_icon.png"));
            stage.setScene(scene);

            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Stobberi TaskBot");
            stage.getIcons().add(icon);

            fxmlLoader.<MainWindow>getController().setStobberi(stobberi);  // inject the Stobberi instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
