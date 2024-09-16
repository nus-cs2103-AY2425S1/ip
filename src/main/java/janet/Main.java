package janet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Janet janet = new Janet("janet.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJanet(janet);  // inject the Janet instance

            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Janet");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
