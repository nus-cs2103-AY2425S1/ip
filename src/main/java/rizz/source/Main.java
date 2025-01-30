package rizz.source;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;


/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private final String dataFilePath = "./data/rizz.txt";

    private final Rizz rizz = new Rizz(dataFilePath);

    public Main() throws IOException {
    }

    @SuppressWarnings("checkstyle:SingleSpaceSeparator")
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(420);
            stage.setMinWidth(420);
            stage.setTitle("RizzBot");
            fxmlLoader.<MainWindow>getController().setRizz(rizz); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
