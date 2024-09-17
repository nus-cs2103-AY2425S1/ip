package bangmang.command;

import bangmang.storage.Storage;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;
import bangmang.exception.InvalidCommandException;
import bangmang.exception.InvalidTaskFormatException;
import bangmang.tasks.Task;

/**
 * Represents a command to unmark a specific task as not done.
 */

public class UnmarkTaskCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an UnmarkTaskCommand with the specified task index.
     *
     * @param i The task number of the task in the list to be unmarked.
     */
    public UnmarkTaskCommand(int i) {
        this.taskIndex = i - 1;
    }

    /**
     * Executes the command to unmark the specified task as not done, saves the updated task list, and provides feedback.
     *
     * @param tasks The list of tasks where the specified task will be unmarked.
     * @param ui The UI instance for providing feedback.
     * @param storage The storage instance to save the updated task list.
     * @return A string indicating the task has been unmarked as not done.
     * @throws InvalidCommandException If the task index is invalid or any other error occurs.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        try {
            Task task = tasks.get(this.taskIndex);
            tasks.unmarkTask(this.taskIndex);
            storage.save(tasks.getTasks());
            return ui.showUnmarkedTask(task);

        } catch (InvalidTaskFormatException e) {
            throw new InvalidCommandException("Alamak, task number out of range. Please provide a valid task number.");
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
