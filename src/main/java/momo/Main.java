package momo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * The Main class serves as the entry point for the JavaFX application. It initializes
 * the main window of the application by loading the FXML layout and preparing the
 * necessary components to be displayed in the stage.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            // Create an instance of MainWindow
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            MainWindow mainWindow = new MainWindow();

            // Set MainWindow as both root and controller
            fxmlLoader.setRoot(mainWindow);
            fxmlLoader.setController(mainWindow);

            AnchorPane ap = fxmlLoader.load(); // Load the FXML
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Inject the Momo instance
            fxmlLoader.<MainWindow>getController().setMomo();

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
