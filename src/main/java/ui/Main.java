package ui;

import bot.Chicken;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Chicken using FXML.
 */
public class Main extends Application {

    private Chicken chicken = new Chicken();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Chicken the bot");
            fxmlLoader.<MainWindow>getController().setChicken(chicken);  // inject the Chicken instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
