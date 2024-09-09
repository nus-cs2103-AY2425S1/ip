import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import spiderman.Spiderman;

public class Main extends Application {

    private Spiderman spiderman = new Spiderman("tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            MainWindow controller = fxmlLoader.getController();
            controller.setSpiderman(spiderman);  // Inject the Spiderman instance
            controller.displayWelcome();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        Platform.exit();
    }
}
