package arsenbot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for ArsenBot using FXML.
 */
public class Main extends Application {

    private ArsenBot arsenBot = new ArsenBot("./data/history.txt");

    @Override
    public void start(Stage stage) {
        stage.setTitle("ArsenBot");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBot(arsenBot);  // inject the ArsenBot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
