import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import barry.Barry;

/**
 * A GUI for Barry using FXML.
 */
public class Main extends Application {

    private Barry barry = new Barry("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(barry);
            stage.show();
            stage.setTitle("Barry");

            MainWindow controller = fxmlLoader.getController();
            controller.setDuke(barry);
            controller.displayWelcomeMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
