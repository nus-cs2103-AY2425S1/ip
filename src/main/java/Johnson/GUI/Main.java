
package Johnson.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Johnson using FXML.
 */
public class Main extends Application {

    private Johnson johnson = new Johnson();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Sergeant Johnson");
            fxmlLoader.<MainWindow>getController().setJohnson(johnson);  // inject the Johnson instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
