package command;

import java.io.IOException;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand object.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Returns the task to be added.
     *
     * @return The task to be added.
     */
    public Task getTask() {
        return task;
    }

    /**
     * Adds the task to the task list, shows a message to indicate the task has been added, and saves the task list to
     * the storage file.
     *
     * @param tasks   The task list to which the task is to be added.
     * @param ui      The UI object to interact with the user.
     * @param storage The storage object to save the task list to the storage file.
     * @return        The response to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            return ui.showSavingError();
        }
        return ui.showTaskAdded(task, tasks.size());
    }
}
