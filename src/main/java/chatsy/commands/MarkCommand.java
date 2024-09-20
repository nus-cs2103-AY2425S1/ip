package chatsy.commands;

import chatsy.TaskManager;
import chatsy.exceptions.InvalidTaskIndexException;

/**
 * Handles the "mark" command which marks a specified task as done.
 */
public class MarkCommand extends Command {

    private final int taskNumber;

    /**
     * Constructs a MarkCommand with the task number to be marked as done.
     *
     * @param arguments The input containing the task number to be marked as done.
     * @throws InvalidTaskIndexException If the task number is invalid.
     */
    public MarkCommand(String arguments) throws InvalidTaskIndexException {
        try {
            this.taskNumber = Integer.parseInt(arguments.trim());
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException("Invalid task index. Please provide a valid number.");
        }
    }

    /**
     * Executes the mark command by marking the specified task as done.
     *
     * @param taskManager The task manager containing the task to be marked.
     * @return A string response confirming the task has been marked as done.
     * @throws InvalidTaskIndexException If the task number is out of range.
     */
    @Override
    public String execute(TaskManager taskManager) throws InvalidTaskIndexException {
        taskManager.markTask(taskNumber);
        return String.format("Nice! I've marked this task as done:\n  %s",
                taskManager.getTasks().get(taskNumber - 1));
    }
}
