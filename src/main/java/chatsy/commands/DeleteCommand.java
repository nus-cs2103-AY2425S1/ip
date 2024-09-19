package chatsy.commands;

import chatsy.TaskManager;
import chatsy.exceptions.InvalidTaskIndexException;

/**
 * Handles the "delete" command which deletes a task.
 */
public class DeleteCommand extends Command {

    private final int taskNumber;

    public DeleteCommand(String arguments) throws InvalidTaskIndexException {
        try {
            this.taskNumber = Integer.parseInt(arguments.trim());
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException();
        }
    }

    @Override
    public String execute(TaskManager taskManager) throws InvalidTaskIndexException {
        taskManager.deleteTask(taskNumber);
        return "Noted. I've removed this task.\nNow you have " + taskManager.getTasks().size() + " tasks in the list.";
    }
}
