package Main;

import Data.Storage;
import Data.StoreList;
import Parser.Parser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;

import java.io.IOException;

/**
 * The Main class serves as the entry point for the JavaFX application.
 * It initializes the primary stage and loads the main user interface from FXML.
 * It also sets up the parser and storage required for the application to run.
 */
public class Main extends Application {

    private Parser parser = new Parser();
    Image title = new Image(this.getClass().getResourceAsStream("/images/Title.png"));

    /**
     * The start method is called when the JavaFX application is launched.
     * It sets up the primary stage by loading the FXML layout and configuring
     * the application window's title, icon, and scene. The method also injects
     * the parser and store list into the controller for further use.
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Load the main layout from the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // Create and set the scene using the loaded layout
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Set the application window's icon and title
            stage.getIcons().add(title);
            stage.setTitle("EmoteX");

            // Inject the parser and store list into the MainWindow controller
            fxmlLoader.<MainWindow>getController().setEmoteX(parser, new StoreList(Storage.loadTasks()));  // inject the Duke instance

            // Display the stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}