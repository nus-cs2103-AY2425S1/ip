package tuesday.interfaceUI;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import tuesday.Tuesday;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Tuesday tuesday = new Tuesday();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setScene(scene);
            // stage.setMaxWidth(417); // Add this if you didn't automatically resize elements
            fxmlLoader.<MainWindow>getController().setTuesday(tuesday);  // inject the tuesday instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
