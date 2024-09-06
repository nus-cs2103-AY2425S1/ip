package serenity.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import serenity.Serenity;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {


    private Serenity serenity = new Serenity();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            // inject the serenity instance
            fxmlLoader.<MainWindow>getController().setSerenity(serenity);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

