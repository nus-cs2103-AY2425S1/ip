package command;

import tasklist.TaskList;
import tasklist.TaskListOutOfBoundsException;
import tasks.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    /** The index of the task to be deleted. */
    protected int index;

    /**
     * Constructs a DeleteCommand with the specified index.
     *
     * @param index The index of the task to be deleted from the task list.
     */
    public DeleteCommand(int index) {
        this.index = index;

    }

    /**
     * Executes the command by deleting the task at the specified index from the task list
     * and displaying the appropriate messages in the command line interface.
     *
     * @param tasklist The TaskList from which the task will be deleted.
     * @return Confirmation of the task deleted.
     */
    @Override
    public String execute(TaskList tasklist) {

        StringBuilder sb = new StringBuilder();

        try {
            Task task = tasklist.delete(index);

            sb.append("Noted. I've removed this task:\n");
            sb.append("  " + task);
            sb.append("Now you have " + tasklist.size() + " tasks in the list.\n");

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
