package monique;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            MainWindow mainWindow = new MainWindow(stage);
            Scene scene = new Scene(mainWindow);
            stage.setScene(scene); // Setting the stage to show our screen
            mainWindow.showWelcomeMessage();
            stage.show(); // Render the stage.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
