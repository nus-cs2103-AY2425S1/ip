package optimus;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Optimus using FXML.
 * Adapted from CS2103T JavaFX tutorial -reused
 */
public class Main extends Application {

    private Optimus optimus;

    /**
     * @author Javier / Incognido
     *
     * @param primaryStage The primary stage for the application
     */
    // Also asked ChatGPT how to implement this method
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setMinHeight(220);
            primaryStage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            // Set the root node
            // Credit to ChatGPT: Suggested setting the root node manually to support fx:root construct
            AnchorPane rootNode = new AnchorPane();
            fxmlLoader.setRoot(rootNode);

            // Load the FXML file
            AnchorPane root = fxmlLoader.load();

            // Get the controller and pass the Duke instance
            MainWindow mainWindow = fxmlLoader.getController();
            optimus = new Optimus("data/optimus.txt", mainWindow);
            mainWindow.setOptimus(optimus);

            //@@author tayxuenye-reused
            // Set the scene and show the stage
            // Credit to ChatGPT: Suggested setting the scene and showing the stage
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Optimus");
            primaryStage.show();
            //@@author tayxuenye-reused
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
