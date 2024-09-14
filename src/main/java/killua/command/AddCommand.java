package killua.command;

import java.io.IOException;

import killua.storage.Storage;
import killua.task.Task;
import killua.ui.Ui;
import killua.util.KilluaException;
import killua.util.TaskList;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private static final String HAS_DUPLICATE_MESSAGE = "Task already exists!";
    private final Task task;

    /**
     * Creates an AddCommand with the specified task to be added.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add the task to the task list.
     * Updates the user interface to show the task has been added and saves the updated task list to storage.
     *
     * @param tasks The task list to which the task will be added.
     * @param ui The user interface to interact with.
     * @param storage The storage to save the updated task list.
     * @return A String representation of Killua's response.
     * @throws KilluaException If there is an error related to task handling.
     * @throws IOException If there is an error in reading or writing to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KilluaException, IOException {
        if (hasDuplicate(tasks)) {
            throw new KilluaException(HAS_DUPLICATE_MESSAGE);
        }
        tasks.addTask(task);
        storage.save(tasks);
        return ui.showTaskAdded(task, tasks.getTasks().size());
    }

    /**
     * Check if the task to be added already in task list.
     *
     * @param tasks The task list to which the task will be added.
     * @return A boolean value.
     */
    private boolean hasDuplicate(TaskList tasks) {
        for (Task currTask : tasks.getTasks()) {
            if (this.task.equals(currTask)) {
                return true;
            }
        }
        return false;
    }
}
