package talkie;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Talkie using FXML.
 * <p>
 * The {@code Main} class serves as the entry point for launching the Talkie application with a graphical user
 * interface (GUI) built using JavaFX. It sets up the primary stage and scene, loads the FXML layout, and injects the
 * {@code Talkie} instance into the controller.
 * </p>
 */
public class Main extends Application {

    private Talkie talkie = new Talkie();

    /**
     * Starts the JavaFX application.
     * <p>
     * This method is called when the JavaFX application is launched. It sets up the primary stage by loading the
     * {@code MainWindow.fxml} layout, creates a scene with the loaded layout, and sets the scene on the stage. The
     * {@code Talkie} instance is injected into the {@code MainWindow} controller for handling user interactions.
     * </p>
     *
     * @param stage The primary stage for this application, onto which the application scene is set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            Image icon = new Image(this.getClass().getResourceAsStream("/images/baby-yoda.png"));

            stage.setScene(scene);
            stage.setTitle("Talkie TaskBot");
            stage.getIcons().add(icon);

            fxmlLoader.<MainWindow>getController().setTalkie(talkie); // inject the Talkie instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
