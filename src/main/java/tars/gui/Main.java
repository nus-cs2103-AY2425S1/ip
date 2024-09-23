package tars.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import tars.Tars;
/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Tars tars = new Tars();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            stage.setTitle("Tars ChatBot");
            Image image = new Image("/images/TarsIcon.png");
            stage.getIcons().add(image);

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTars(tars);  // inject the Tars instance

            stage.setMinHeight(220);
            stage.setMinWidth(417);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

