package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Represents implementation of Application
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        //Creating label control
        Label talker = new Label("Talker");
        // setting scene to be talker
        Scene scene = new Scene(talker);
        stage.setScene(scene);
        stage.show();
    }
}
