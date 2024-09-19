import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import krona.Krona;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Krona krona = new Krona("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/css/main.css").toExternalForm());
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            MainWindow controller = fxmlLoader.getController();
            fxmlLoader.<MainWindow>getController().setKrona(krona);
            controller.displayWelcomeMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
