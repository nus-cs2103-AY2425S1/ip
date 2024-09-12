package skibidi;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Skibidi using FXML.
 */
public class Main extends Application {

    private Skibidi skibidi = new Skibidi();

    /**
     * Starts the application.
     *
     * @param stage The stage to display the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSkibidi(skibidi);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading MainWindow.fxml");
        }
    }
}
