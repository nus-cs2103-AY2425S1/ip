package lemon.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lemon.Lemon;
import lemon.app.Launcher;

/**
 * A GUI for Duke using FXML.
 */
public class LemonFx extends Application {
    private final Lemon lemon = new Lemon();
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LemonFx.class.getResource("/view/LemonFxWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Launcher.out.setWindow(fxmlLoader.getController());

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Lemon Chatbot");
            fxmlLoader.<LemonFxWindow>getController().setLemon(lemon); // inject the Lemon instance
            stage.show();

            lemon.intro();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
