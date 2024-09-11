package tudee.command;

import tudee.storage.Storage;
import tudee.task.TaskList;
import tudee.ui.Ui;

/**
 * Represents a command that handles unknown or invalid commands.
 * This command notifies the user that the entered command is unknown.
 */
public class UnknownCommand extends Command {
    /**
     * Executes the command to handle an unknown or invalid command.
     * Updates the user interface to display an error message to show that the command is not recognized.
     *
     * @param tasks The task list (not used in this command).
     * @param ui The user interface to update with the error message.
     * @param storage The storage (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // Assert that tasks, ui and storage are not null.
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        return ui.showError("Unknown command! Please try again.");
    }
}
