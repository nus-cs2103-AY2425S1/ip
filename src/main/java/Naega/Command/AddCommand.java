package Naega.Command;

import Naega.Storage.Storage;
import Naega.Task.Task;
import Naega.Task.TaskList;
import Naega.Ui.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Creates an AddCommand with the specified task.
     *
     * @param task the task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by adding the task to the task list, updating the UI, and saving the tasks to storage.
     *
     * @param tasks   the task list to which the task will be added
     * @param ui      the UI component to display information to the user
     * @param storage the storage component to save the updated task list
     * @return a string message that confirms the task has been added, including the task details and the updated task count
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        return ui.printTaskAdded(task, tasks.size());
    }
}