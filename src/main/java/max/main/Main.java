package max.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Max max = new Max();

    /**
     * The main entry point for the JavaFX application. It initializes the primary stage,
     * loads the FXML layout for the main window, and sets the scene for the stage.
     * It also injects the Max instance into the MainWindow controller to enable interaction
     * between the GUI and the application logic.
     *
     * @param stage The primary stage for this application, onto which the application scene will be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            stage.setTitle("Max");

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(max);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}