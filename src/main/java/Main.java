import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import prince.Prince;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Prince prince;

    /*public Main(String filePath) {
        this.prince = new Prince("./data", filePath); // Create a Prince instance with the provided file path
    }*/

    @Override
    public void start(Stage stage) {
        this.prince = new Prince("./data", "data/prince.Prince.txt");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            stage.setMinHeight(220);
            stage.setMinWidth(417);

            fxmlLoader.<MainWindow>getController().setPrince(prince);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }*/
}
