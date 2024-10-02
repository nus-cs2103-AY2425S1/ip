package moimoi;

import javafx.application.Application;
import javafx.stage.Stage;
import moimoi.gui.MainWindow;

/**
 * A GUI for MoiMoi using FXML.
 */
public class MainApp extends Application {

    private MoiMoi moiMoi = new MoiMoi("data/moimoi.txt");

    /**
     * The main entry point for the chatbot program (MoiMoi).
     *
     * @param stage The primary stage (window) for the application.
     */
    @Override
    public void start(Stage stage) {
        new MainWindow(moiMoi).show();
    }

}
