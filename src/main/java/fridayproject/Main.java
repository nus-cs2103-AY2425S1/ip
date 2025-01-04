package fridayproject;

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

    private Friday friday = new Friday("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {

            // Ensure that the FXML file path is correct
            assert Main.class.getResource("/view/MainWindow.fxml") != null : "FXML file not found";

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            
            // Ensure that the AnchorPane is not null
            assert ap != null : "AnchorPane should not be null";

            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Ensure that the controller is not null
            assert fxmlLoader.<MainWindow>getController() != null : "Controller should not be null";

            fxmlLoader.<MainWindow>getController().setFriday(friday);  // inject the Friday instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

