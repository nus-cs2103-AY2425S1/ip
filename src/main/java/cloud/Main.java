package cloud;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Cloud cloud = new Cloud();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            // Set the root node
            AnchorPane rootNode = new AnchorPane();
            fxmlLoader.setRoot(rootNode);

            AnchorPane root = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController().setCloud(cloud); // inject the Duke instance


            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.show();
            stage.setTitle("Cloud");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
