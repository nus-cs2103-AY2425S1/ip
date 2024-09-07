package yappingbot;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    public void start(Stage stage) throws Exception {
        Label helloWorld = new Label("asdad");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }
}
