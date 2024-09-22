package ava.gui;

import java.io.IOException;

import ava.AVA;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Handles the GUI
 */
public class Gui extends Application {

    private final AVA ava;

    /**
     * Creates a GUI instance for a fresh AVA.
     */
    public Gui() {
        this(new AVA());
    }

    /**
     * Creates a GUI instance with the specified AVA instance.
     *
     * @param ava the AVA instance to be used
     */
    public Gui(AVA ava) {
        this.ava = ava;
    }

    /**
     * Displays the main GUI
     *
     * @param stage the primary stage for this application, onto which
     *                     the application scene will be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAVA(ava);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
