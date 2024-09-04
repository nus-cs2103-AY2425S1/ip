package optimus.commands;

import optimus.Storage;
import optimus.TaskList;
import optimus.Ui;
import optimus.exceptions.OptimusExceptions;

/**
 * Template for Commands
 */
public abstract class Command {

    /**
     * Returns if programme should continue
     *
     * @return - True except for the exit command
     */
    public boolean shouldContinue() {
        return true;
    }

    public abstract void execute(Storage storage, TaskList tasks, Ui ui) throws OptimusExceptions;
}
