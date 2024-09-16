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

    private Primo primo = new Primo("data/data.txt");

    /**
     * Instantiates FXML, stages the scene with App title and App icon.
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            stage.setTitle("El Primo Chatbot");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));

            Scene scene = new Scene(ap);
            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setPrimo(primo);  // inject the Primo instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
