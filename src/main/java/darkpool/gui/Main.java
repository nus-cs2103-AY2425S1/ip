package darkpool.gui;

import darkpool.Darkpool;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Main class is the entry point of the application.
 * It loads the MainWindow.fxml file and sets the Darkpool object to the controller.
 */
public class Main extends Application {

    private final Darkpool darkpool = new Darkpool();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setDarkpool(darkpool);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}