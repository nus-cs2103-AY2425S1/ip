package fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import moody.Moody;

import java.io.IOException;

public class Main extends Application {
    private static final String DEFAULT_FILE_PATH = "./data/moody.txt";
    private Moody moody = new Moody(DEFAULT_FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMoody(moody);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
