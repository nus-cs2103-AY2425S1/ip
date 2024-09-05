import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Downy using FXML.
 */
public class Main extends Application {

    private Downy downy = new Downy("data/tasks.txt");
    private static Stage primaryStage; // Reference to the primary stage

    @Override
    public void start(Stage stage) {
        try {
            primaryStage = stage; // Set primary stage reference
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Downy");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDowny(downy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to exit the application
    public static void exitApp() {
        primaryStage.close();
    }
}
