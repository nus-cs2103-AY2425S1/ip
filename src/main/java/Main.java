import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import killua.Killua;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Killua killua = new Killua("./data/tasks.txt", stage);
            assert killua != null : "Killua instance is not initialized!";
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            assert fxmlLoader != null : "FXMLLoader is not initialized!";
            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "AnchorPane is not loaded!";
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow controller = fxmlLoader.getController();
            assert controller != null : "MainWindow controller is not loaded!";
            controller.setKillua(killua);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
