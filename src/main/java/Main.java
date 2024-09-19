import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Susan using FXML.
 */
public class Main extends Application {
    // Create Susan object to be used for generating responses
    private Susan susan = new Susan();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("SusanLite");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSusan(susan);  // inject the Susan instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
