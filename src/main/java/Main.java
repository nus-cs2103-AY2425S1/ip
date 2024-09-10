import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mahesh.Mahesh;

/**
 * A GUI for Mahesh using FXML.
 */
public class Main extends Application {

    private Mahesh mahesh = new Mahesh();

    /**
     * Starts the application by setting up the main stage and loading the FXML layout.
     *
     * @param stage the primary stage for this application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(mahesh);  // inject the Mahesh instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}