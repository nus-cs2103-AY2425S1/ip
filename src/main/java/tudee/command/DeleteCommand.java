package tudee.command;

import tudee.TudeeException;
import tudee.storage.Storage;
import tudee.task.Task;
import tudee.task.TaskList;
import tudee.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 * This command removes the task at a specified index.
 * Updates the user interface and storage accordingly.
 */
public class DeleteCommand extends Command {
    private static final int MINIMUM = 1;
    private static final int INDEX_OFFSET = 1;
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete the task at the specified index.
     * Updates the user interface to show the deleted task.
     * Saves the updated task list to storage.
     *
     * @param tasks The task list from which the task will be deleted.
     * @param ui The user interface to update with the deleted task.
     * @param storage The storage to save the updated task list.
     * @throws TudeeException If the specified index is out of range.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TudeeException {
        // Assert that tasks, ui and storage are not null.
        validateInputs(tasks, ui, storage);
        validateIndex(index, tasks.numOfTasks());

        Task task = tasks.deleteTask(index - INDEX_OFFSET);
        storage.save(tasks.getTasks());
        return ui.showDelete(task, tasks.numOfTasks());
    }

    /**
     * Validates that the provided index is within the valid range.
     *
     * @param index The index to be validated.
     * @param numOfTasks The number of tasks in the task list.
     * @throws TudeeException If the index is out of range.
     */
    private void validateIndex(int index, int numOfTasks) throws TudeeException {
        if (index > numOfTasks) {
            throw new TudeeException("You do not have that many tasks!");
        } else if (index < MINIMUM) {
            throw new TudeeException("You cannot have negative tasks!");
        }
    }
}
