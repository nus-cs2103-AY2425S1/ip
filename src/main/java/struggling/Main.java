package struggling;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import struggling.gui.MainWindow;

/**
 * A GUI for Struggling using FXML.
 */
public class Main extends Application {

    private final Struggling bot = new Struggling("data/Struggling.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader
                    .<MainWindow>getController()
                    .setBot(bot);
            stage
                    .getIcons()
                    .add(new Image(this
                            .getClass()
                            .getResourceAsStream("/images/user.gif")));
            stage.setTitle("Struggling");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
