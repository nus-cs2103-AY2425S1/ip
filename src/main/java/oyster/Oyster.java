package oyster;

import javafx.application.Application;
import javafx.stage.Stage;
import oyster.ui.Ui;

/**
 * Entrypoint to the chatbot.
 */
public class Oyster extends Application {
    public static final String CHATBOT_EMOJI = "\uD83E\uDDAA";
    public static final String CHATBOT_NAME = "OYSTER CHATBOT " + CHATBOT_EMOJI;

    private Ui ui;

    /**
     * Starts the application window.
     *
     * @param stage The base stage provided by javaFX.
     */
    @Override
    public void start(Stage stage) {
        ui = new Ui(this, stage);
        LogicController.begin();
    }

    /**
     * Supplies the logic with an input.
     *
     * @param input The input to supply.
     * @return An array of String as a response message.
     */
    public String[] readInput(String input) {
        return LogicController.readInput(input);
    }
}
