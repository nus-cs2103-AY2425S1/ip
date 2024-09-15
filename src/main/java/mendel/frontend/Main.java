package mendel.frontend;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mendel.main.Mendel;

/**
 * The Main class handles managing the frontend GUI that is to be displayed
 */
public class Main extends Application {
    private final Mendel mendel = new Mendel();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Mendel");
            stage.getIcons().add(new Image("/images/img.png"));
            fxmlLoader.<MainWindow>getController().setMendel(mendel);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
