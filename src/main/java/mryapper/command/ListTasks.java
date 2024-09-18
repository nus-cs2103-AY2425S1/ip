package mryapper.command;

import mryapper.storage.Storage;
import mryapper.task.TaskList;

/**
 * A command which displays all the task.
 */
public class ListTasks extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.toString();
    }
}
