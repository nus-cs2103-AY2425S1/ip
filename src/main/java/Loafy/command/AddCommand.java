package loafy.command;

import loafy.task.Task;
import loafy.tasklist.TaskList;
import loafy.ui.Ui;

/**
 * Represents a command to add the task to a task list.
 * Each instance of AddCommand is tied to an instance of Task.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an add command for the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task in this command to the end of the specified task list.
     * Prints a message to confirm the addition of the task.
     *
     * @param tasks Task list to which the task will be added to.
     * @param ui User interface which will print the message.
     */
    public String execute(TaskList tasks, Ui ui) {
        return tasks.add(this.task);
    }
}
