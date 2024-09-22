package ava.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Handles the GUI
 */
public class Gui extends Application {
    /**
     * Displays the main GUI
     *
     * @param stage the primary stage for this application, onto which
     *                     the application scene will be set.
     */
    @Override
    public void start(Stage stage) {
        Label test = new Label("AVA");
        Scene scene = new Scene(test);

        stage.setScene(scene);
        stage.show();
    }
}
