package command;

import exception.KukiShinobuException;
import storage.Storage;
import task.TaskList;

/**
 * Represents a command that marks a specific task as not done.
 * The {@code UnmarkTaskCommand} updates the status of a task at a specified index
 * to indicate that it has not been completed.
 */
public class UnmarkTaskCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a {@code UnmarkTaskCommand} with the specified task index.
     *
     * @param arguments The index of the task to be marked as not done, passed as a string.
     *                  This string is parsed into an integer to identify the task.
     * @throws KukiShinobuException if the arguments cannot be parsed into an integer.
     */
    public UnmarkTaskCommand(String arguments) throws KukiShinobuException {
        try {
            this.taskIndex = Integer.parseInt(arguments.trim());
        } catch (NumberFormatException e) {
            throw new KukiShinobuException("The task index must be a number.");
        }

        // Optional: Validate that taskIndex is positive
        if (this.taskIndex < 0) {
            throw new KukiShinobuException("The task index cannot be negative.");
        }
    }

    /**
     * Executes the unmark task command.
     * This method marks the task at the specified index as not done in the task list.
     *
     * @param taskList The task list containing the tasks to be updated.
     * @param storage  The storage object, which is not used in this command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws KukiShinobuException {
        return taskList.unmarkAsDone(taskIndex);
    }
}
