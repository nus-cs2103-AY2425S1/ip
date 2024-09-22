package brainrot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The BrainRot.Main class serves as the entry point for the application.
 * It initializes the BrainRot.BrainRot instance with the file path where tasks are stored
 * and then starts the main loop to process user commands.
 */
public class Main extends Application{

    private BrainRot brainRot;

//    /**
//     * The main method is the entry point of the Java application.
//     * It constructs a BrainRot.BrainRot instance with the specified file path for task storage
//     * and then runs the application.
//     *
//     * @param args Command-line arguments passed to the program (not used).
//     */
//
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));

            // Initialize BrainRot instance
            String filePath = System.getProperty("user.home") + "/ip/data/brainRot.txt";
            brainRot = new BrainRot(filePath);
            AnchorPane root = fxmlLoader.load();

            // Set the Duke instance in the controller
            MainWindow controller = fxmlLoader.getController();
            controller.setBrainRot(brainRot);

            // Create a new scene with the AnchorPane as the root node
            Scene scene = new Scene(root);

            // Set up the stage
            stage.setScene(scene);
            stage.setTitle("brainrot");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
