package snipe.command;

import snipe.core.TaskList;
import snipe.exception.SnipeException;
import snipe.storage.Storage;
import snipe.task.Task;
import snipe.util.Ui;

import java.io.IOException;

/**
 * The {@code AddCommand} class represents a command to add a new task to the task list.
 * This command adds the specified task, saves the updated task list to storage, and
 * provides feedback to the user via the user interface.
 */
public class AddCommand extends Command{
    private final Task task;

    /**
     * Constructs an {@code AddCommand} with the specified task to be added.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        // Assert that the task is not null
        assert task != null : "Task to be added should not be null";
        this.task = task;
    }

    /**
     * Executes the add command by adding a new task to the task list, saving the updated list to storage,
     * and returning a confirmation message to the user.
     *
     * @param tasks   The {@link TaskList} to which the new task will be added.
     * @param ui      The {@link Ui} instance used to display messages and interact with the user.
     * @param storage The {@link Storage} instance responsible for persisting the updated task list.
     * @return A confirmation message indicating that the task has been successfully added, along with the updated task count.
     * @throws SnipeException If an application-specific error occurs while saving the task list.
     * @throws IOException    If an I/O error occurs during the saving process.
     */
    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws SnipeException, IOException {
        // Assert that the TaskList is not null
        assert tasks != null : "TaskList should not be null";
        // Assert that the Storage is not null
        assert storage != null : "Storage should not be null";

        tasks.addTask(this.task);
        storage.saveTaskList(tasks);
        return "Got it. I've added this task:\n  " + this.task + tasks.listLength();
    }

}
