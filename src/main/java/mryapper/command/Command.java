package mryapper.command;

import mryapper.exception.InvalidSyntaxException;
import mryapper.storage.Storage;
import mryapper.task.TaskList;

public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks The TaskList to execute the command on.
     * @param storage The Storage to execute the command on.
     * @return The response from the ChatBot to the user.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws InvalidSyntaxException;
}
