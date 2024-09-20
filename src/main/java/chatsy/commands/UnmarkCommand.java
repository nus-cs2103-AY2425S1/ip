package chatsy.commands;

import chatsy.TaskManager;
import chatsy.exceptions.InvalidTaskIndexException;

/**
 * Handles the "unmark" command which marks a task as not done.
 */
public class UnmarkCommand extends Command {

    private final int taskNumber;

    /**
     * Constructs an {@code UnmarkCommand} with the task number to unmark.
     *
     * @param arguments The input containing the task number to be marked as not done.
     * @throws InvalidTaskIndexException If the task number is invalid.
     */
    public UnmarkCommand(String arguments) throws InvalidTaskIndexException {
        try {
            this.taskNumber = Integer.parseInt(arguments.trim());
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException("Invalid task index. Please provide a valid number.");
        }
    }

    /**
     * Executes the unmark command by marking the specified task as not done.
     *
     * @param taskManager The task manager containing the task to be marked.
     * @return A string response confirming the task has been marked as not done.
     * @throws InvalidTaskIndexException If the task number is out of range.
     */
    @Override
    public String execute(TaskManager taskManager) throws InvalidTaskIndexException {
        taskManager.unmarkTaskAsNotDone(taskNumber);
        return String.format("OK, I've marked this task as not done yet:\n  %s",
                taskManager.getTasks().get(taskNumber - 1));
    }
}
