package noosy.commands;

import noosy.exception.NoosyException;
import noosy.storage.Storage;
import noosy.task.Task;
import noosy.task.TaskList;
import noosy.ui.Ui;


/**
 * Represents a command to add a task to the task list.
 * This command adds a new task and updates the user interface and storage.
 */
public class AddCommand extends Command {

    /**
     * The task to be added to the task list.
     */
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task the task to be added to the task list
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add a task to the task list.
     * It updates the task list, displays the task added message to the user,
     * and saves the updated task list to storage.
     *
     * @param tasks the task list to which the task will be added
     * @param ui the user interface to display feedback to the user
     * @param storage the storage system to save the updated task list
     * @throws NoosyException if there is an issue adding the task
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoosyException {
        tasks.add(task);
        ui.showTaskAdded(tasks, task);
        storage.addTask(task);
    }
}
