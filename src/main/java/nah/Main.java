package nah;

import javafx.application.Application;
import javafx.stage.Stage;
import nah.GUI.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Nah nah = new Nah();

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow(nah);
        mainWindow.setStage(stage);
    }
}
