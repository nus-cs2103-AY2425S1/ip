package command;

import tasklist.TaskList;
import tasklist.TaskListOutOfBoundsException;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    /** The index of the task to be marked as done. */
    protected int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as done in the task list.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the task at the specified index as done in the task list,
     * and displaying the appropriate messages in the command line interface.
     *
     * @param tasklist The TaskList where the task will be marked as done.
     * @return Confirmation of the task marked.
     */
    @Override
    public String execute(TaskList tasklist) {
        StringBuilder sb = new StringBuilder();

        try {
            tasklist.mark(index);

            sb.append("Nice! I've marked this task as done:\n");
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
