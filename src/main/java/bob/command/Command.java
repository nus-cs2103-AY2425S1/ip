package bob.command;

import bob.Storage;
import bob.TaskList;

/**
 * Represents an abstract command class.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Storage storage);

}