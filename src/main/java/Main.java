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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCharlotte(charlotte); // inject the Charlotte instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

