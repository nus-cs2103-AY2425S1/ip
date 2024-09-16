package monique;

import java.util.Objects;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * The main entry point for the JavaFX application.
 * This class extends {@code Application} and sets up the primary stage for the application.
 * It initializes the main window and displays it to the user.
 */
public class Main extends Application {

    /**
     * The main entry point for the JavaFX application.
     * This method is called by the JavaFX runtime to start the application.
     * It sets up the primary stage, initializes the main window, and shows it.
     *
     * @param stage the primary stage for this application, onto which scenes can be set
     */
    @Override
    public void start(Stage stage) {
        try {
            MainWindow mainWindow = new MainWindow(stage);
            Scene scene = new Scene(mainWindow);
            stage.setScene(scene); // Setting the stage to show our screen
            stage.setTitle("Monique: Your personal helper");
            Image moniqueImage = new Image(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream("/images/monique_logo.png")));
            stage.getIcons().add(moniqueImage);
            mainWindow.showWelcomeMessage();
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.show(); // Render the stage.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
