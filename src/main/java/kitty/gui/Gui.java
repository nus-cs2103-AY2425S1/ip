package kitty.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kitty.Kitty;

public class Gui extends Application {
    private final Kitty kitty = new Kitty();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Kitty");

            // inject the Duke instance
            fxmlLoader.<MainWindow>getController().setKitty(kitty);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

