package taskon.commands;

import taskon.storage.Storage;
import taskon.task.TaskList;
import taskon.ui.Ui;

/**
 * Represents a command that handles incorrect or unrecognized user input.
 * <p>
 * This class is used to display an error message when the user enters an
 * invalid or unrecognized command.
 * </p>
 */
public class IncorrectCommand extends Command {

    public final String result;

    /**
     * Constructs an {@code IncorrectCommand} with the specified error message.
     *
     * @param result The error message to display.
     */
    public IncorrectCommand(String result) {
        this.result = result;
    }

    /**
     * Executes the command to display an error message.
     * <p>
     * This method updates the user interface to show the specified error message.
     * </p>
     *
     * @param taskList The list of tasks (not used in this command).
     * @param ui       The user interface that handles the display of the error message.
     * @param storage  The storage (not used in this command).
     * @return A string message that describes the error.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showError(result);
    }
}
