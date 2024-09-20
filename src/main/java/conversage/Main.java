package conversage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private ConverSage conversage = new ConverSage("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setConversage(conversage);  // inject the Conversage instance

            // Set the application title
            stage.setTitle("ConverSage");

            // Set the application icon
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/DaDuke.png")));


            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
