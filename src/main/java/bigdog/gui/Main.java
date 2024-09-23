package bigdog.gui;

import java.io.IOException;

import bigdog.Bigdog;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Bigdog using FXML.
 * This class serves as the entry point for the JavaFX application, initializing and showing
 * the main window and passing the Bigdog instance to the controller.
 */
public class Main extends Application {

    /**
     * Instance of the Bigdog class.
     */
    private final Bigdog bigdog = new Bigdog("data/bigdog.txt");

    /**
     * Starts the JavaFX application.
     *
     * @param stage The primary stage for this application, onto which the application scene is set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Bigdog");
            fxmlLoader.<MainWindow>getController().setBigdog(bigdog);
            stage.show();
            System.out.println("open");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
