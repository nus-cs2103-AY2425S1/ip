package yappingbot;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class to launch GUI Application.
 */
public class Main extends Application {
    static String savefilePath;

    /**
     * Static method to launch Main without GUI.
     * Use as fallback method.
     *
     * @param savefilePath String path to use for savefile, or "" to use default.
     * @param args String ArrayList of arguments passed in via CLI when launching this app.
     */
    public static void launch(String savefilePath, String[] args) {
        Main.savefilePath = savefilePath.isEmpty() ? "./savefile" : savefilePath;
        Main.launch(Main.class, args);
    }

    /**
     * Static method to launch Main with JavaFX GUI.
     *
     * @param customSavefilePath String path to use for savefile, or "" to use default.
     * @param args String ArrayList of arguments passed in via CLI when launching this app.
     */
    public static void launchCli(String customSavefilePath, String[] args) {
        Main.savefilePath = customSavefilePath.isEmpty() ? "./savefile" : customSavefilePath;
        YappingBot yp = new YappingBot(Main.savefilePath);
        yp.start();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            VBox vb = fxmlloader.load();
            Scene scene = new Scene(vb);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
