package sigmabot.command;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Greeting extends Command {

    @Override
    public void execute(TextArea displayArea, TextField inputField) {
        greet(displayArea);
    }

    public static void greet(TextArea displayArea) {
        displayArea.appendText("Hello! Welcome to SigmaBot. How can I assist you today?\n");
    }
}
