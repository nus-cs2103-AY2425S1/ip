package command;

import components.Storage;
import components.Ui;
import task.Task;
import task.TaskList;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Creates an AddCommand object.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Adds the task to the task list and updates the storage file.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        storage.write(TaskList.arrayToNumberedString(tasks));
        ui.showMessage("Got it. I've added this task:\n" +
                task +
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
