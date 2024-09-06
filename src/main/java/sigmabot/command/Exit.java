package sigmabot.command;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * The Exit class represents a command that closes the application.
 * It provides the functionality to execute the exit process,
 * which in this case, terminates the JavaFX application.
 */
public class Exit extends ChatCommand {

    /**
     * Executes the exit command.
     * This method terminates the JavaFX application.
     */
    @Override
    public void execute(TextArea displayArea, TextField inputField) {
        displayArea.appendText("Exiting the application...\n");
        Platform.exit();  // Terminate the JavaFX application
    }
}
