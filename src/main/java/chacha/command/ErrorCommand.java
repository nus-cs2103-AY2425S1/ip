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
     * Returns the string representation of response to user inputting
     * a command that the chatbot cannot recognise.
     *
     * @param userInput
     * @param storage
     * @param ui
     * @param tasks
     * @return String representation.
     */
    @Override
    public String execute(String userInput, Storage storage, Ui ui, TaskList tasks) {
        return ui.printError();
    }
}
