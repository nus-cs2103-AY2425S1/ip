package nether.command;

import nether.Ui;
import nether.storage.Storage;
import nether.task.Task;
import nether.task.TaskList;

/**
 * Represents a command to add a task to the task list.
 * The {@code AddCommand} class is responsible for adding a specified task to the task list,
 * printing a confirmation message, and saving the updated task list to storage.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an {@code AddCommand} with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command by adding the task to the task list, printing a confirmation message,
     * and saving the updated task list to storage.
     *
     * @param tasks The {@code TaskList} to which the task will be added.
     * @param ui The {@code Ui} instance used to print the task addition message.
     * @param storage The {@code Storage} instance used to save the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.printTaskAdded(task);
        storage.saveTasks(tasks.getTasks());
    }
}
