package joe.commands;

import joe.exceptions.InvalidIndexException;
import joe.tasks.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    private final TaskList taskList;
    private final int index;

    /**
     * Constructs a DeleteCommand object.
     * @param taskList Task list to delete task from.
     * @param index Index of task to delete.
     */
    public DeleteCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        assert(index >= 0);
        this.index = index;
    }

    @Override
    public String execute() throws InvalidIndexException {
        return taskList.deleteTask(index);
    }
}
