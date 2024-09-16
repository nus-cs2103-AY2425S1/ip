package shoai;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private ShoAI chatbot = new ShoAI("src/main/data/ShoAI.txt", "src/main/data/clients.txt");

    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // Create the scene with the loaded AnchorPane
            Scene scene = new Scene(ap);

            // Add the CSS file to the scene
            scene.getStylesheets().add(getClass().getResource("/view/styles.css").toExternalForm());

            // Set the scene to the stage
            stage.setScene(scene);

            // Set the title of the stage
            stage.setTitle("ShoAI");

            // Show the stage
            stage.show();

            // Inject the chatbot instance into the controller
            MainWindow controller = fxmlLoader.getController();
            controller.setChatbot(chatbot);  // Inject the chatbot instance
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
