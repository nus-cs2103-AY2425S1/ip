package gopher.gui;

import java.io.IOException;

import gopher.Gopher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Gopher using FXML.
 */
public class Main extends Application {

    private Gopher gopher = new Gopher();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setGopher(gopher);
            stage.getIcons().add(new Image("images/Gopher.png"));
            stage.setTitle("Gopher");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
