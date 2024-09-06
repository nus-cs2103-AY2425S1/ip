package sigmabot.command;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GreetingCommand extends ChatCommand {

    @Override
    public void execute(TextArea displayArea, TextField inputField) {
        greet(displayArea);
    }

    /**
     * Executes the greeting command.
     */
    public static void greet(TextArea displayArea) {
        displayArea.appendText("Hello! Welcome to SigmaBot. How can I assist you today?\n");
    }
}
