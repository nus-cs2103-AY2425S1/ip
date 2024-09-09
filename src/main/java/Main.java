import java.io.IOException;

import Components.MainWindow;
import Components.MrIncredible;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private MrIncredible mrIncredible = new MrIncredible();

    @Override
    public void start(Stage stage) {
        // Assert that the mrIncredible instance is not null before injecting
        assert mrIncredible != null : "MrIncredible instance must not be null";

        try {
            // Ensure the FXML resource is correctly loaded
            assert Main.class.getResource("/view/MainWindow.fxml") != null : "FXML file not found";

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // Assert that the AnchorPane is loaded properly
            assert ap != null : "AnchorPane could not be loaded";

            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Ensure the controller is set correctly before showing the stage
            assert fxmlLoader.<MainWindow>getController() != null : "MainWindow controller must not be null";

            fxmlLoader.<MainWindow>getController().setDuke(mrIncredible);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

