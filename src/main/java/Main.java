import java.io.IOException;

import charlotte.Charlotte;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Charlotte using FXML.
 */
public class Main extends Application {

    private Charlotte charlotte = new Charlotte("data/charlotte.txt");

    /**
     * Starts the application by setting up the primary stage with the main window.
     *
     * @param stage The primary stage for this application, onto which the scene will be set.
     */
    @Override
    public void start(Stage stage) {
        assert charlotte != null : "Charlotte instance should not be null";

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            assert fxmlLoader.getLocation() != null : "FXML file '/view/MainWindow.fxml' should be found";

            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "AnchorPane should be initialised from the FXML file";

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            assert stage.getScene() != null : "Scene should be properly set in the stage";

            fxmlLoader.<MainWindow>getController().setCharlotte(charlotte); // inject the Charlotte instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

