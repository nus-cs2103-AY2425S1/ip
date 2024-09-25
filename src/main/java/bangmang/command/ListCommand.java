package bangmang.command;

import bangmang.storage.Storage;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;
import bangmang.exception.InvalidCommandException;

/**
 * Represents a command that displays all tasks in the task list.
 */

public class ListCommand extends Command {
    /**
     * Executes the list command by requesting the UI to display all tasks or a message indicating no tasks are present.
     *
     * @param tasks The list of tasks to be displayed.
     * @param ui The UI instance for displaying task lists or messages.
     * @param storage The storage instance (not used in this command).
     * @return A string representing the task list or a message if no tasks are present.
     * @throws InvalidCommandException If any error occurs while processing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        if (tasks.size() == 0) {
            return ui.showNoTasks();
        } else {
            return ui.showAllTasks(tasks.getTasks());
        }
    }

    /**
     * Returns false as this command does not terminate the application.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
