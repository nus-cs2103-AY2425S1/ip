package sigmabot.command;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * The Joke class represents a command that triggers a joke to be told.
 * It provides the functionality to execute the joke-telling process,
 * which outputs a joke to the GUI.
 */
public class Joke extends ChatCommand {

    /**
     * Executes the joke command.
     * This method outputs a placeholder joke message to the display area.
     * The actual implementation can be extended to provide a variety of jokes.
     */
    @Override
    public void execute(TextArea displayArea, TextField inputField) {
        // Display a joke in the TextArea
        displayArea.appendText("Why did the scarecrow win an award? Because he was outstanding in his field!\n");
    }
}
