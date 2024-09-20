import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for D++E using FXML.
 */
public class Main extends Application {

    private DPlusPlusE dPlusPlusE = new DPlusPlusE();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("D++E");
            fxmlLoader.<MainWindow>getController().setDPlusPlusE(dPlusPlusE);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
