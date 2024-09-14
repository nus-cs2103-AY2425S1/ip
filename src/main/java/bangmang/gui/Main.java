package bangmang.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import LittleMissHelpful.LittleMissHelpful;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private LittleMissHelpful littleMissHelpful = new LittleMissHelpful("data/LittleMissHelpful.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLittleMissHelpful(littleMissHelpful);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
