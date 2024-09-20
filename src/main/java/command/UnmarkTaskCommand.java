package command;

import exception.KukiShinobuException;
import exception.InvalidCommandKukiShinobuException;
import storage.Storage;
import task.TaskList;

/**
 * Represents a command that marks a specific task as not done.
 * The {@code UnmarkTaskCommand} updates the completion status of a task at a specified index
 * to indicate that it has not been completed by the user.
 */
public class UnmarkTaskCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an {@code UnmarkTaskCommand} with the specified task index.
     * The task index is provided as a string and parsed into an integer to reference a specific task.
     *
     * @param arguments The index of the task to be marked as not done, passed as a string.
     *                  This string is trimmed and parsed into an integer.
     * @throws InvalidCommandKukiShinobuException if the provided arguments cannot be parsed into a valid integer
     *                                            or if the task index is negative.
     */
    public UnmarkTaskCommand(String arguments) throws InvalidCommandKukiShinobuException {
        try {
            this.taskIndex = Integer.parseInt(arguments.trim());
        } catch (NumberFormatException e) {
            throw new InvalidCommandKukiShinobuException("The task index must be a number.");
        }

        // Validate that the task index is not negative
        if (this.taskIndex < 0) {
            throw new InvalidCommandKukiShinobuException("The task index cannot be negative.");
        }
    }

    /**
     * Executes the unmark task command.
     * This method marks the task at the specified index as not done within the {@code taskList}.
     * If the task is successfully marked as not done, it updates the task's status and returns a confirmation message.
     *
     * @param taskList The task list containing the task to be updated.
     * @param storage  The storage object, which is not used in this command.
     * @return A string message indicating that the task has been successfully unmarked as not done.
     * @throws KukiShinobuException if there is an error while unmarking the task (e.g., invalid task index).
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws KukiShinobuException {
        return taskList.unmarkAsDone(taskIndex);
    }
}
