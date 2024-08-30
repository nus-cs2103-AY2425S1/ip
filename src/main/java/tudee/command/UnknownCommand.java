package tudee.command;

import tudee.task.TaskList;
import tudee.ui.Ui;
import tudee.storage.Storage;

/**
 * Represents a command that handles unknown or invalid commands.
 * This command notifies the user that the entered command is unknown.
 */
public class UnknownCommand extends Command {
    /**
     * Executes the command to handle an unknown or invalid command.
     * Updates the user interface to display an error message indicating
     * that the command is not recognized.
     *
     * @param tasks The task list (not used in this command).
     * @param ui The user interface to update with the error message.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError("Unknown command! Please try again.");
    }
}