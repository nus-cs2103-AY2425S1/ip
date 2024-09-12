import java.io.IOException;

import dave.Dave;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Dave dave = new Dave("C:\\Users\\thamy\\OneDrive\\data\\daveData.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDave(dave); // inject the Duke instance
            fxmlLoader.<MainWindow>getController().showWelcomeMessage();
            stage.setMinHeight(220);
            stage.setMinWidth(640);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}