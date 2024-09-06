package sigmabot.command;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TerminateCommand extends ChatCommand {

    @Override
    public void execute(TextArea displayArea, TextField inputField) {
        displayArea.appendText("Terminating the application...\n");

        // Exit the JavaFX application gracefully
        Platform.exit();
    }
}
