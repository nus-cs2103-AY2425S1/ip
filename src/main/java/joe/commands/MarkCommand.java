package joe.commands;

import joe.exceptions.InvalidIndexException;
import joe.tasks.TaskList;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private final TaskList taskList;
    private final int index;

    /**
     * Constructs a MarkCommand object.
     * @param taskList The task list to mark the task in.
     * @param index The index of the task to be marked.
     */
    public MarkCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        assert(index >= 0);
        this.index = index;
    }

    @Override
    public String execute() throws InvalidIndexException {
        return taskList.markDone(index);
    }
}
