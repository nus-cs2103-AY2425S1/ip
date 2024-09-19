import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Sigma using FXML.
 */
public class Main extends Application {
    private static final String DEFAULT_FILE_PATH = "C:\\Users\\limqi\\OneDrive\\Desktop\\uni\\CS2103T\\ip\\data\\sigma.txt";
    private Sigma sigma = new Sigma(DEFAULT_FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Sigma");
            fxmlLoader.<MainWindow>getController().setSigma(sigma);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
