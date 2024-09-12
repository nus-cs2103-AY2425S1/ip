import java.io.IOException;

import dave.Dave;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Dave dave = new Dave("C:\\Users\\thamy\\OneDrive\\data\\daveData.txt");

    @Override
    public void start(Stage stage) {
        try {
            assert dave != null : "Dave instance should be initialized"; // Assert that dave is initialized

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            assert ap != null : "AnchorPane should have been loaded"; // Assert that the layout was successfully loaded

            Scene scene = new Scene(ap);
            stage.setScene(scene);

            MainWindow controller = fxmlLoader.<MainWindow>getController();
            assert controller != null : "MainWindow controller should be initialized";
            // Assert controller initialization

            controller.setDave(dave); // inject the Duke instance
            controller.showWelcomeMessage();

            stage.setMinHeight(220);
            stage.setMinWidth(640);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
