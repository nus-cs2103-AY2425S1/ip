package mryapper.command;

import mryapper.storagemanager.StorageManager;
import mryapper.task.TaskList;

@FunctionalInterface
public interface Command {

    public String execute(TaskList tasks, StorageManager storageManager);
}
