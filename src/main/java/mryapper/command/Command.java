package mryapper.command;

import mryapper.storagemanager.StorageManager;
import mryapper.task.TaskList;

public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks The TaskList to execute the command on.
     * @param storageManager The Storage to execute the command on.
     * @return The response from the ChatBot to the user.
     */
    public abstract String execute(TaskList tasks, StorageManager storageManager);
}
