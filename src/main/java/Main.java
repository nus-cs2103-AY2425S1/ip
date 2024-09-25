import java.io.IOException;

import barry.Barry;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Barry using FXML.
 */
public class Main extends Application {

    private Barry barry = new Barry("data/tasks.txt");

    /**
     * Starts the application.
     *
     * @param stage The stage to start the application on.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Barry");
            fxmlLoader.<MainWindow>getController().setBarry(barry);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
