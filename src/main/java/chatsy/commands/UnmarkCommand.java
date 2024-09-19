package chatsy.commands;

import chatsy.TaskManager;
import chatsy.exceptions.InvalidTaskIndexException;

/**
 * Handles the "unmark" command which marks a task as not done.
 */
public class UnmarkCommand extends Command {

    private final int taskNumber;

    public UnmarkCommand(String arguments) throws InvalidTaskIndexException {
        try {
            this.taskNumber = Integer.parseInt(arguments.trim());
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException();
        }
    }

    @Override
    public String execute(TaskManager taskManager) throws InvalidTaskIndexException {
        taskManager.unmarkTaskAsNotDone(taskNumber);
        return "OK, I've marked this task as not done yet:\n  " + taskManager.getTasks().get(taskNumber - 1);
    }
}
