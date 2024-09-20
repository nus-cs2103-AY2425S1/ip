package chatsy.commands;

import chatsy.TaskManager;

/**
 * Handles the "list" command which lists all tasks managed by the task manager.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by returning a list of all tasks in the task manager.
     *
     * @param taskManager The task manager containing the tasks to be listed.
     * @return A string response listing all tasks.
     */
    @Override
    public String execute(TaskManager taskManager) {
        return taskManager.listTasks();
    }
}
