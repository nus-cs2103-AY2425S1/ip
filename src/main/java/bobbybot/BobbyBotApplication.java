package bobbybot;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * A GUI for BobbyBot using FXML.
 */
public class BobbyBotApplication extends Application {

    protected BobbyBot bobbyBot;
    @Override
    public void init() {
        bobbyBot = new BobbyBot();
    }

    @Override
    public void start(Stage stage) {
        bobbyBot.ui.start(stage, bobbyBot);
    }
}
