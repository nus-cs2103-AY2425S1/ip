package TanjiroBot;

import java.io.IOException;

import Controller.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    /**
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception
     */

    private Tanjiro tanjiro = new Tanjiro();
    private final Image BOT_IMAGE = new Image(this.getClass().getResourceAsStream("/images/dslogo.png"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            stage.setMinHeight(500);
            stage.setMinWidth(700);
            stage.setTitle("Tanjiro Bot");
            stage.getIcons().add(BOT_IMAGE);

            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindowController>getController().setTanjiro(tanjiro);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
