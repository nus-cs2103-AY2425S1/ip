package matcha.command;

import matcha.storage.Storage;
import matcha.tasklist.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListTaskCommand extends Command {

    /**
     * Executes the command to list all tasks in the task list.
     *
     * @param tasks The task list to list all tasks from.
     * @param storage Storage object to save tasks to file.
     * @return The response to the user.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        super.setResponse(tasks.listTasks());
        return super.getResponse();
    }
}
