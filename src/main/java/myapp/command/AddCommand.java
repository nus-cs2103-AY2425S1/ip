package myapp.command;

import myapp.task.Task;
import myapp.task.TaskList;

/**
 * Represents an abstract command that adds a task to the task list.
 * This class provides common functionality for adding tasks and
 * printing the message after a task is added.
 */
public abstract class AddCommand extends Command {
    /** The description of the task to be added. */
    String description;

    /**
     * Constructs an AddCommand with the specified task description.
     *
     * @param s The description of the task to be added.
     */
    public AddCommand(String s) {
        super();
        this.description = s;
    }

    /**
     * Returns false since an AddCommand does not terminate the application.
     *
     * @return false, indicating that this command does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns a message indicating that a task has been added to the task list.
     *
     * @param tasks The task list to which the task has been added.
     * @param t The task that was added.
     * @return A string message indicating the task has been added and showing the current number of tasks.
     */
    public String printAddMessage(TaskList tasks, Task t) {
        return "Got it. I've added this task:\n" + t
                + "\n" + "Now you have " + tasks.size()
                + " tasks in the list";
    }
}
