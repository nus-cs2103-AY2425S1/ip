package yapyap;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class for the YapperBot application.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource("/css/dialog-box.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("YapperBot");
            stage.show();

            MainWindow controller = fxmlLoader.getController();
            controller.setYapperBot(new YapperBot());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading fxml file");
        }
    }
}
