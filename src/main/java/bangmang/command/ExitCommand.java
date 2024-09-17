/**
 * Represents a command to exit the application and save the current state.
 */
package bangmang.command;

import bangmang.storage.Storage;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;
import bangmang.exception.InvalidCommandException;

public class ExitCommand extends Command {

    /**
     * Executes the exit command, saves the current task list, and returns the exit message.
     *
     * @param tasks The list of tasks to save.
     * @param ui The UI instance for displaying messages.
     * @param storage The storage instance for saving tasks.
     * @return The exit message to be displayed.
     * @throws InvalidCommandException If any error occurs while saving tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        storage.save(tasks.getTasks());
        return ui.showExit();
    }

    /**
     * Returns true to indicate that this command is an exit command.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
