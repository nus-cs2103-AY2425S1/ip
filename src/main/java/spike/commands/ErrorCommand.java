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
        this.message = message;
    }

    /**
     * Returns the message of the error command.
     *
     * @return The message of the error command.
     */
    @Override
    public String getCommandType() {
        return this.message;
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
