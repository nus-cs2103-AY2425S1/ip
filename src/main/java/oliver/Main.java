package oliver;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Oliver using FXML.
 */
public class Main extends Application {

    private Oliver oliver = new Oliver("./data/oliver.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setOliver(oliver);
            stage.show();
        } catch (IOException e) {
            System.out.println("Unable to start the GUI for oliver.");
        }
    }
}