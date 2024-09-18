package mryapper.command;

import mryapper.storagemanager.StorageManager;
import mryapper.task.TaskList;

/**
 * A command which displays all the task.
 */
public class ListTasks extends Command {

    @Override
    public String execute(TaskList tasks, StorageManager storage) {
        return tasks.toString();
    }
}
