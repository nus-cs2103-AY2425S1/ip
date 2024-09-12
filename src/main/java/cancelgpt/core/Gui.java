package cancelgpt.core;

import cancelgpt.gui.GuiWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;


/**
 * Test GUI.
 */
public class Gui extends Application {

    private CancelGpt cancelGpt = new CancelGpt(Paths.get(System
            .getProperty("user.home"), "accountexeregister-ip", "data"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/GuiWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<GuiWindow>getController().setCancelGpt(cancelGpt); // inject the CancelGpt instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
