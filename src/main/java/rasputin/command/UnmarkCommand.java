package rasputin.command;

import rasputin.task.TaskList;
import rasputin.task.InvalidTaskException;


/**
 * Represents a command to mark a task as not done.
 */
public class UnmarkCommand extends Command {

    private TaskList tasks;
    private int index;

    public UnmarkCommand(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    /**
     * Marks the task at the given index of the TaskList as not done.
     *
     * @throws InvalidTaskException If the given index is out of bounds of the TaskList.
     */
    @Override
    public String execute() throws InvalidTaskException {
        try {
            tasks.unmark(index);
            String output = "Task has been unmarked.\n" + tasks.get(index).toString();
            return output;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException("ERROR! Task not found.");
        }
    }

    /**
     * Always returns false.
     *
     * @return False.
     */
    @Override
    public boolean isTerminated() {
        return false;
    }
}
