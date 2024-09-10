package sigma;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The {@code Main} class serves as the entry point for launching the Sigma GUI
 * application using JavaFX and FXML. This class initializes the main window
 * and sets up the scene for user interaction.
 */
public class Main extends Application {
    private static final int MINHEIGHT = 220;
    private static final int MINWIDTH = 417;
    private Sigma sigma = new Sigma();

    /**
     * Starts the JavaFX application by setting up the primary stage, loading the FXML
     * layout, and displaying the main window.
     *
     * @param stage The primary stage for this application, onto which the application scene
     *              is set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(MINHEIGHT);
            stage.setMinWidth(MINWIDTH);
            fxmlLoader.<MainWindow>getController().setSigma(sigma);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
