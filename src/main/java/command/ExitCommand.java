package command;

import ui.Ui;
import storage.Storage;
import task.TaskList;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command.
     *
     * @param tasks TaskList object that contains the list of tasks.
     * @param ui Ui object that interacts with the user.
     * @param storage Storage object that reads and writes to the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // No action needed
    }

    /**
     * Returns true if the command is an exit command.
     *
     * @return true if the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}