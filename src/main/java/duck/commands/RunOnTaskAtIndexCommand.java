package duck.commands;

import duck.Parser;
import duck.TaskList;
import duck.exceptions.InvalidTaskListIndexException;
import duck.exceptions.RunOnTaskAtIndexUsageException;
import duck.exceptions.TaskListIndexOutOfBoundsException;

/**
 * Class representing commands where the user specify a task list index to
 * execute an action on a single task.
 */
public abstract class RunOnTaskAtIndexCommand extends Command {
    protected TaskList taskList;
    private Parser lineBuffer;
    private RunOnTaskAtIndexUsageException usageException;

    /**
     * Constructor for RunOnTaskAtIndexCommand.
     *
     * @param taskList       List of tasks.
     * @param lineBuffer     Buffer containing remaining command.
     * @param usageException The cause of this exception.
     */
    public RunOnTaskAtIndexCommand(TaskList taskList, Parser lineBuffer,
            RunOnTaskAtIndexUsageException usageException) {
        this.taskList = taskList;
        this.lineBuffer = lineBuffer;
        this.usageException = usageException;
    }

    protected abstract String executeOnTask(TaskList taskList, int taskLabel);

    private int parseIndex() throws InvalidTaskListIndexException, TaskListIndexOutOfBoundsException {
        int taskLabel;
        try {
            taskLabel = lineBuffer.getInt();
        } catch (NumberFormatException e) {
            throw new InvalidTaskListIndexException(usageException);
        }

        try {
            taskList.getItem(taskLabel);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskListIndexOutOfBoundsException(taskLabel, taskList.getTaskCount(), usageException);
        }

        return taskLabel;
    }

    @Override
    public String executeCommand() {
        try {
            int taskLabel = parseIndex();
            String response = executeOnTask(taskList, taskLabel);
            return response;
        } catch (InvalidTaskListIndexException e) {
            return e.toString();
        } catch (TaskListIndexOutOfBoundsException e) {
            return e.toString();
        }
    }
}
