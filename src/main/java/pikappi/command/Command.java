package pikappi.command;

import pikappi.Storage;
import pikappi.TaskList;
import pikappi.Ui;
import pikappi.exception.PikappiException;

/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {
    private Storage storage;
    private TaskList tasks;

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws PikappiException;
}
