
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sinatra.Sinatra;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Sinatra sinatra = new Sinatra();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSinatra(sinatra);  // inject the Sinatra instance
            stage.show();
//            new Sinatra();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
