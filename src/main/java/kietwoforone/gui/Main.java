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

    @Override
    public void start(Stage stage) {
        assert stage != null: "Stage cannot be null";
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
