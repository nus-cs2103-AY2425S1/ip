package chatsy.commands;

import chatsy.TaskManager;
import chatsy.exceptions.InvalidTaskIndexException;

/**
 * Handles the "delete" command which deletes a task from the task manager.
 * The task to be deleted is identified by its task number.
 */
public class DeleteCommand extends Command {

    private final int taskNumber;

    /**
     * Creates a new DeleteCommand with the given arguments.
     * The arguments should contain the task number to be deleted.
     *
     * @param arguments The input string containing the task number.
     * @throws InvalidTaskIndexException If the task number is not a valid integer.
     */
    public DeleteCommand(String arguments) throws InvalidTaskIndexException {
        try {
            this.taskNumber = Integer.parseInt(arguments.trim());
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException("Invalid task index. Please provide a valid number.");
        }
    }

    /**
     * Executes the delete command by removing the specified task from the task manager.
     *
     * @param taskManager The task manager from which the task is deleted.
     * @return A string response confirming the task has been removed.
     * @throws InvalidTaskIndexException If the task number is out of range.
     */
    @Override
    public String execute(TaskManager taskManager) throws InvalidTaskIndexException {
        taskManager.deleteTask(taskNumber);
        return String.format("Noted. I've removed this task.\nNow you have %d tasks in the list.",
                taskManager.getTasks().size());
    }
}
