package luke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Luke built on FXML.
 */
public class LukeUI extends Application {

    private Luke luke = new Luke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LukeUI.class.getResource("/view/LukeUiWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<LukeUiWindow>getController().setLuke(luke); // inject the Luke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
