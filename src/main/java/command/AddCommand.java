package command;

import ui.Ui;
import storage.Storage;
import task.Task;
import task.TaskList;

import java.io.IOException;

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
     * @param tasks The task list to which the task is to be added.
     * @param ui The UI object to interact with the user.
     * @param storage The storage object to save the task list to the storage file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showSavingError();
        }
    }
}
