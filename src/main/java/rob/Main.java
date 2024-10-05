package rob;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Rob using FXML.
 */
public class Main extends Application {
    private static final String FILE_PATH = "./data/robsaved.txt";
    private Rob rob = new Rob(FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Rob");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRob(rob); // inject the Rob instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
