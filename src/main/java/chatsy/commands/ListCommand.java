package chatsy.commands;

import chatsy.TaskManager;

/**
 * Handles the "list" command which lists all tasks.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskManager taskManager) {
        return taskManager.listTasks();
    }
}

