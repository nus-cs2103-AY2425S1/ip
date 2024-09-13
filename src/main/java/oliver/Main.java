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
    private final double MIN_HEIGHT = 220;
    private final double MIN_WIDTH = 417;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Oliver");
            stage.setScene(scene);
            stage.setMinHeight(MIN_HEIGHT);
            stage.setMinWidth(MIN_WIDTH);
            fxmlLoader.<MainWindow>getController().setOliver(oliver);
            stage.show();
        } catch (IOException e) {
            System.out.println("Unable to start the GUI for oliver.");
        }
    }
}