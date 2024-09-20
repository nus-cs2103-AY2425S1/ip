import java.io.IOException;

import ai.Ai;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Ai using FXML.
 */
public class Main extends Application {

    private Ai ai = new Ai("./data/Ai.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Ai - Powered by Ai Hoshino");
            fxmlLoader.<MainWindow>getController().setAi(ai); // inject the Ai instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
