package gui;

import java.io.IOException;
import java.util.Objects;

import chatterbox.ChatterboxGui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main application for javaFx
 */
public class Main extends Application {

    private ChatterboxGui chatterbox = new ChatterboxGui();
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Image icon = new Image(Objects.requireNonNull(
                    Main.class.getResourceAsStream("/images/Chatterboxicon.png")));
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(420);
            stage.setMinWidth(417);
            stage.setTitle(chatterbox.getName());
            stage.getIcons().add(icon);
            fxmlLoader.<MainWindow>getController().setChatterbox(chatterbox); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
