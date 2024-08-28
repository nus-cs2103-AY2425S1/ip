package commands;

import main.TaskList;
import ui.Ui;
import storage.Storage;

/**
 * Represents a command that is invalid or unrecognized by the application.
 * This command displays an error message to the user, indicating that the input was not valid.
 */
public class InvalidCommand extends Command {
    private final String errorMessage;

    /**
     * Constructs an {@code InvalidCommand} with the specified error message.
     *
     * @param errorMessage the error message to be displayed to the user
     */
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Executes the invalid command by displaying the error message to the user.
     * Since this command is for invalid inputs, it does not modify the task list or storage.
     *
     * @param taskList the list of tasks (not used in this command)
     * @param ui the user interface for interacting with the user
     * @param storage the storage handler (not used in this command)
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showError(errorMessage);
    }
}
