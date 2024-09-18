package jude;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jude.ui.MainWindow;

/**
 * A GUI for Jude using FXML.
 */
public class Main extends Application {
    private static final String DEFAULT_FILE_PATH = "data/jude.txt";

    private Jude jude = new Jude(DEFAULT_FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            MainWindow controller = fxmlLoader.getController();

            // Assert jude should not be null
            assert jude != null : "jude should not be null";

            controller.setJude(jude);

            controller.setStage(stage);
            stage.show();
            controller.displayWelcomeMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
