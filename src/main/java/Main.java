import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Alice using FXML.
 */
public class Main extends Application {
    private Alice alice = new Alice("data.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setTitle("Alice");
            stage.setScene(scene);
            MainWindow window = fxmlLoader.getController();
            window.setAlice(alice);
            window.start();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
