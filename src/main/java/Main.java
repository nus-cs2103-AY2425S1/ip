import java.io.IOException;
import java.util.Locale;
import java.util.TimeZone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import xbot.XBot;

/**
 * A GUI for XBot using FXML.
 */
public class Main extends Application {

    private XBot xbot = new XBot();

    @Override
    public void start(Stage stage) {
        try {
            // Set default locale to Singapore
            Locale.setDefault(new Locale("en", "SG"));
            // Set default time zone to Singapore
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Singapore"));
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setXBot(xbot); // inject the XBot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
