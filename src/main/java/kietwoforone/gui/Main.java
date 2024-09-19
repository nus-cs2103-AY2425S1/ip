package kietwoforone.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kietwoforone.KieTwoForOne;

/**
 * A GUI for KieTwoForOne using FXML.
 */
public class Main extends Application {

    private KieTwoForOne kie = new KieTwoForOne();

    /**
     * Sets up the primary stage and initializes the user interface when the application is launched.
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setKie(kie);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
