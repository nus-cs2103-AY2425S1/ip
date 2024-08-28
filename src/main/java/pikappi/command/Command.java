package pikappi.command;

import pikappi.exception.PikappiException;
import pikappi.Storage;
import pikappi.TaskList;
import pikappi.Ui;

/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {
    private Storage storage;
    private TaskList tasks;

    public Command() {
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws PikappiException;
}
