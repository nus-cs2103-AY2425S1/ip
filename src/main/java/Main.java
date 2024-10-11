import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class.
 */
public class Main extends Application {
    private Luke luke = new Luke("./data/Luke.txt");

    /**
     * Returns an instance of Mainl
     *
     * @throws IOException When file path cannot be used.
     */
    public Main() throws IOException {
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLuke(luke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



