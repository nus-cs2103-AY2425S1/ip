package drbrown.command;

import drbrown.task.Task;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

/**
 * Represents a command to add a task to the task list.
 * This command encapsulates the task to be added and provides the logic
 * for executing the addition of the task to the task list and displaying
 * relevant messages to the user.
 */
public class AddCommand extends Command {

    private final Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        assert task != null : "Task should not be null";
        this.task = task;
    }

    /**
     * Executes the add command by adding the task to the task list,
     * displaying a message indicating that the task has been successfully added,
     * and updating the task count display.
     *
     * @param tasks   The TaskList to which the task is added.
     * @param ui      The UI object used to display messages to the user.
     * @param storage The Storage object used for saving tasks to a file.
     * @return A string message that confirms the task addition and shows the updated task count.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "Tasks should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        tasks.add(task);
        return ui.showTaskCreation(tasks, task);
    }

    /**
     * Indicates whether this command results in the termination of the program.
     *
     * @return false, as this command does not cause the program to exit.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
