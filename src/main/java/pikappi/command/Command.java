package pikappi.command;

import pikappi.exception.PikappiException;
import pikappi.Storage;
import pikappi.TaskList;
import pikappi.Ui;

public abstract class Command {
    private Storage storage;
    private TaskList tasks;

    public Command() {
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws PikappiException;
}
