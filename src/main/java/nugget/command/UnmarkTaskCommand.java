package nugget.command;

import nugget.Storage;
import nugget.TaskList;
import nugget.Ui;
import nugget.exception.InvalidTaskNumberException;
import nugget.exception.NuggetException;

/**
 * Represents a command to unmark a task in the task list.
 * The {@code UnmarkTaskCommand} allows the user to mark a previously completed
 * task as incomplete.
 */
public class UnmarkTaskCommand implements Command {
    private int index;

    /**
     * Constructs an {@code UnmarkTaskCommand} with the specified task index.
     *
     * @param index The index of the task to be unmarked in the task list.
     */
    public UnmarkTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark task command by unmarking the task at the specified index,
     * saving the updated task list, and displaying the unmarked task using the {@code Ui}.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The {@code Ui} object used to interact with the user.
     * @param storage The {@code Storage} object used to handle file saving.
     * @throws NuggetException If the task index is invalid or if there is an error during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NuggetException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        tasks.unmarkTask(index);
        storage.saveTasks(tasks.getTasks());
        ui.showUnmarkedTask(tasks.getTask(index));
    }
}
