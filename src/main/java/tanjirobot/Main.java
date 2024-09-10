package tanjirobot;

import java.io.IOException;

import controller.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * The Main class serves as the entry point for the Tanjiro Bot application.
 * It extends Application class from JavaFX and is responsible for
 * initializing and displaying the primary stage of the application.
 */
public class Main extends Application {
    private Tanjiro tanjiro = new Tanjiro();
    private Image logo = new Image(this.getClass().getResourceAsStream("/images/dslogo.png"));
    /**
     * @param stage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            stage.setMinHeight(500);
            stage.setMinWidth(700);
            stage.setTitle("Tanjiro Bot");
            stage.getIcons().add(logo);

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
