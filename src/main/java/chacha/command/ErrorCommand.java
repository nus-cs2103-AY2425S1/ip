package chacha.command;

import chacha.ChaCha;
import chacha.Storage;
import chacha.Ui;
import chacha.task.TaskList;

/**
 * Represents the command to return error message to user.
 */
public class ErrorCommand extends Command {
    public ErrorCommand(ChaCha chacha) {
        super(chacha);
    }

    /**
     * Returns the string representation of the error response to user inputting
     * a command that the chatbot cannot recognise.
     *
     * @param userInput User input
     * @param storage Storage of ChaCha
     * @param ui UI of ChaCha
     * @param tasks List of tasks
     * @return String representation.
     */
    @Override
    public String execute(String userInput, Storage storage, Ui ui, TaskList tasks) {
        if (userInput.equals("")) {
            return ui.printBlankError();
        }
        return ui.printError();
    }
}
