package yappingbot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Main class to launch GUI Application.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Label helloWorld = new Label("asdad");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }
}
