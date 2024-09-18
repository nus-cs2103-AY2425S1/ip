package jude;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jude.ui.MainWindow;

/**
 * A GUI for Jude using FXML.
 */
public class Main extends Application {
    private static final String DEFAULT_FILE_PATH = "data/jude.txt";

    private Jude jude = new Jude(DEFAULT_FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            MainWindow mainWindow = new MainWindow();
            fxmlLoader.setController(mainWindow);
            fxmlLoader.setRoot(mainWindow);
            fxmlLoader.load();

            stage.setMinHeight(220);
            stage.setMinWidth(417);

            Scene scene = new Scene(mainWindow);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJude(jude); // inject the Jude instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
