package joe;

import javafx.application.Application;
import javafx.stage.Stage;
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

    @Override
    public void stop() {
        joe.endProgram();
    }

}
