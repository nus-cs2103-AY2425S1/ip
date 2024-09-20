package jbot.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jbot.JBot;


/**
 * A utility class for handling GUI using FXML.
 */
public class Ui extends Application {
    private JBot jbot = new JBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("JBot");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJbot(jbot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
