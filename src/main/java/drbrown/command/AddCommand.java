package drbrown.command;

import drbrown.task.Task;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {

    private final Task task;

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
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showTaskCreation(task);
        ui.showCount(tasks);
    }

    /**
     * Indicates whether this command exits the program.
     *
     * @return false, as this command does not exit the program.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
