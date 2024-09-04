package sigmabot.command;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Terminate extends Command {
    @Override
    public void execute(TextArea displayArea, TextField inputField) {
        displayArea.appendText("Goodbye!\n");
        System.exit(0); // Close the application
    }
}
