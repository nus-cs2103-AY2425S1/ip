package dgpt;

import java.io.IOException;
import java.util.Locale;
import java.util.TimeZone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Dgpt dgpt = new Dgpt("./data/dgpt.txt");

    @Override
    public void start(Stage stage) {
        try {
            Locale.setDefault(new Locale("en", "SG"));
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Singapore"));
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("DGPT");
            fxmlLoader.<dgpt.fxml.MainWindow>getController().setDgpt(dgpt); // inject the Dgpt instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
