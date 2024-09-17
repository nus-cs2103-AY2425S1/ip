package lebron;

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

    private static final String DEFAULT_FILE_PATH = "./data/lebron.txt";

    private LeBron lebron = new LeBron(DEFAULT_FILE_PATH);

    @Override
    public void start(Stage stage) {
        stage.setTitle("LeBron James - ChatBot");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(lebron.Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<lebron.MainWindow>getController().setDuke(lebron);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
