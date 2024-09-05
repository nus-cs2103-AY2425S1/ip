package joe;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import joe.controller.Controller;
import joe.parser.Parser;
import joe.ui.MainWindow;
import joe.ui.Ui;

/**
 * A GUI for Duke using FXML.
 */
public class JoeApp extends Application {

    private Joe joe;

    @Override
    public void init() {
        joe = new Joe();
    }

    @Override
    public void start(Stage stage) {
        Ui ui = joe.getUi();
        ui.start(stage, joe.getParser());
    }

}
