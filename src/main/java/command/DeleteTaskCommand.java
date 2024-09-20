package command;

import storage.Storage;
import task.TaskList;
import exception.KukiShinobuException;
import exception.InvalidCommandKukiShinobuException;

/**
 * Represents a command to delete a task from the task list.
 */

public class DeleteTaskCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteTaskCommand with the specified index of the task to be deleted.
     * <p>
     * If the provided index is not a valid integer, a {@code InvalidCommandKukiShinobuException} is thrown
     * with an error message indicating the issue.
     * </p>
     *
     * @param indexString The index of the task as a string.
     *                    It will be parsed into an integer.
     * @throws InvalidCommandKukiShinobuException If the indexString is not a valid integer.
     */
    public DeleteTaskCommand(String indexString) throws InvalidCommandKukiShinobuException {
        try {
            this.taskIndex = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            throw new InvalidCommandKukiShinobuException("Invalid task index: Please enter a numeric value.");
        }
    }

    /**
     * Executes the delete task command. Deletes the task from the task list
     * and handles any necessary UI and storage updates.
     *
     * @param taskList The list of tasks to delete the task from.
     * @param storage  The storage to save changes made to the task list.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws KukiShinobuException {
        return taskList.deleteTask(taskIndex);
    }
}
