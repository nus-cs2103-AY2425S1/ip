package star.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Star using FXML.
 */
public class Main extends Application {
    private Star star = new Star("data/star.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            MainWindow controller = fxmlLoader.getController();
            controller.setStar(star); // inject the Star instance
            controller.showStarStartMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
