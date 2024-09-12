package choaticbot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The entry point for the ChoaticBot application using JavaFX.
 * This class extends the {@link Application} class and provides
 * the starting point for the JavaFX application.
 */
public class Main extends Application {

    /**
     * The main entry point for JavaFX applications.
     * This method is called when the application is launched.
     * It sets up the primary stage with a simple "Hello World!" label.
     *
     * @param stage The primary stage for this application, onto which
     *              the application scene is set.
     */
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our scene
        stage.show(); // Render the stage.
    }
}

