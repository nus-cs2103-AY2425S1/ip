package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.MainWindow;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private KukiShinobu kukiShinobu = new KukiShinobu();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Get the controller and set the KukiShinobu instance and the Stage reference
            MainWindow controller = fxmlLoader.getController();
            controller.setKukiShinobu(kukiShinobu);
            controller.setStage(stage);
            stage.show();
            controller.displayWelcomeMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
