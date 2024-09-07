package wenjiebot.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import wenjiebot.WenJie;

/**
 * A GUI for Duke using FXML. This class serves as the main entry point for the
 * graphical user interface (GUI) version of the WenJie bot.
 */
public class Main extends Application {
    private WenJie wenJie = new WenJie("./src/main/java/data/wenjie.txt");

    /**
     * Starts the JavaFX application and sets up the main window.
     *
     * @param stage the primary stage for this application
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setWenJie(wenJie);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
