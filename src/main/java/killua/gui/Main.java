package killua.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import killua.Killua;

/**
 * A GUI for Killua using FXML.
 */
public class Main extends Application {
    private static final String MAINWINDOW_FXML_PATH = "/view/MainWindow.fxml";
    /**
     * Starts the JavaFX application by setting up the stage, scene, and loading the MainWindow FXML layout.
     *
     * @param stage The primary stage for this JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        try {
            Killua killua = new Killua();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(MAINWINDOW_FXML_PATH));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Killua Chatbot");
            fxmlLoader.<MainWindow>getController().setKillua(killua);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
