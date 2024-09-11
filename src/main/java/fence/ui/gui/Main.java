package fence.ui.gui;

import java.io.IOException;

import fence.Fence;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Fence using FXML.
 */
public class Main extends Application {

    private Fence fence = new Fence();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setFence(fence); // inject the Fence instance
            fxmlLoader.<MainWindow>getController().initializeGreetingDialog();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
