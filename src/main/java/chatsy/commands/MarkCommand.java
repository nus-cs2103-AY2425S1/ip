package chatsy.commands;

import chatsy.TaskManager;
import chatsy.exceptions.InvalidTaskIndexException;

/**
 * Handles the "mark" command which marks a task as done.
 */
public class MarkCommand extends Command {

    private final int taskNumber;

    public MarkCommand(String arguments) throws InvalidTaskIndexException {
        try {
            this.taskNumber = Integer.parseInt(arguments.trim());
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException();
        }
    }

    @Override
    public String execute(TaskManager taskManager) throws InvalidTaskIndexException {
        taskManager.markTask(taskNumber);
        return "Nice! I've marked this task as done:\n  " + taskManager.getTasks().get(taskNumber - 1);
    }
}
