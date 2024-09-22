package grok;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.Ui;

/**
 * A GUI for Grok using FXML.
 */
public class Main extends Application {

    private Grok grok = new Grok();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Ui.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<Ui>getController().setGrok(grok);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
