package ekud.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Runs the EKuD chatbot GUI.
 *
 * @author uniqly
 */
public class Main extends Application {
    private static final String XML_SOURCE_PATH = "/view/MainWindow.fxml";
    private static final String ICON_IMAGE_PATH = "/images/upside-down.png";
    private static final String TITLE = "EKuD";
    /** Minimum height of the app window */
    private static final int MIN_HEIGHT = 220;
    /** Minimum width of the app window */
    private static final int MIN_WIDTH = 417;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(XML_SOURCE_PATH));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(MIN_HEIGHT);
            stage.setMinWidth(MIN_WIDTH);

            // set title and icon
            stage.setTitle(TITLE);
            stage.getIcons().add(new Image(ICON_IMAGE_PATH));

            // inject the Duke instance
            fxmlLoader.<MainWindow>getController().setEkud();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
