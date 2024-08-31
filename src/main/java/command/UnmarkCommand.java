package command;

import tasklist.TaskList;
import tasklist.TaskListOutOfBoundsException;

/**
 * Represents a command to unmark a task as not done in the task list.
 */
public class UnmarkCommand extends Command {
    /** The index of the task to be unmarked as not done. */
    protected int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to be unmarked as not done in the task list.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by unmarking the task at the specified index as not done in the task list,
     * and displaying the appropriate messages in the command line interface.
     *
     * @param tasklist The TaskList where the task will be unmarked as not done.
     * @return Confirmation of the task unmarked.
     */
    @Override
    public String execute(TaskList tasklist) {
        StringBuilder sb = new StringBuilder();

        try {
            tasklist.unmark(index);

            sb.append("OK, I've marked this task as not done yet:\n");
            sb.append("  " + tasklist.getTask(index));
        } catch (TaskListOutOfBoundsException e) {
            sb.append(e.getMessage());
        }

        return sb.toString();
    }

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return false, as this command does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
