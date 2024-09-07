package ipman;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Ui extends Application {

    private IpMan ipMan;

    @Override
    public void start(Stage stage) {
        try {
            ipMan = new IpMan("data/saved.txt");
            ipMan.init();
            FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setIpMan(ipMan);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
