package drbrown.command;

import drbrown.utils.Storage;
import drbrown.task.Task;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command by adding the task to the task list,
     * displaying the task creation message and updating the task count.
     *
     * @param tasks   The TaskList to which the task is added.
     * @param ui      The UI object to display messages to the user.
     * @param storage The Storage object for saving tasks to a file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskCreation(this.task);
        tasks.add(this.task);
        ui.showCount(tasks);
    }

    /**
     * Indicates whether this command exits the program.
     *
     * @return false, as this command does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
