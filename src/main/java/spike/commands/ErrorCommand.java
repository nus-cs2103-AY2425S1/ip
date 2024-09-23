package spike.commands;

import spike.storage.Storage;
import spike.storage.TaskList;
import spike.ui.Ui;

/**
 * Represents an error command that is shown whenever an error occurs.
 */
public class ErrorCommand extends Command {
    private final String message;

    /**
     * Constructs an error command with the specified error message.
     *
     * @param message The error message.
     */
    public ErrorCommand(String message) {
        assert message != null : "Error message cannot be null";
        this.message = message;
    }

    /**
     * Executes the error command by showing the error message to the user.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert ui != null : "User interface cannot be null";
        ui.showExceptionMessage(message);
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
