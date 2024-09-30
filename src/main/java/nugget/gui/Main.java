package nugget.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nugget.Nugget;

/**
 * The main JavaFX application class for the Nugget Task Tracker.
 * This class is responsible for initializing the UI and setting up the Nugget backend for task management.
 */
public class Main extends Application {

    /**
     * The starting point for the JavaFX application.
     * This method sets up the primary stage, loads the FXML layout, and initializes the {@code Nugget} instance.
     *
     * @param primaryStage The primary stage for this application, onto which the application scene is set.
     * @throws Exception If there is an issue loading the FXML layout.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ChatUi.fxml"));
        VBox root = loader.load();

        ChatUiController controller = loader.getController();
        Nugget nugget = new Nugget("data/nugget.txt", controller);
        controller.setNugget(nugget);

        Scene scene = new Scene(root, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Nugget Task Tracker");
        primaryStage.show();

        nugget.start();
    }
}
