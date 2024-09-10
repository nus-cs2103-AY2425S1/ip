package joe.commands;

import joe.exceptions.InvalidIndexException;
import joe.tasks.TaskList;

/**
 * Represents a command to unmark a task as done in the task list.
 */
public class UnmarkCommand extends Command {
    private final TaskList taskList;
    private final int index;

    /**
     * Constructs an UnmarkCommand object.
     * @param taskList The task list to unmark the task in.
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        assert(index >= 0);
        this.index = index;
    }

    @Override
    public String execute() throws InvalidIndexException {
        return taskList.unmark(index);
    }
}
