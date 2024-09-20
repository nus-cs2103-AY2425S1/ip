package optimus.commands;

import optimus.Storage;
import optimus.TaskList;
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

    public abstract String execute(Storage storage, TaskList tasks) throws OptimusExceptions;
}
