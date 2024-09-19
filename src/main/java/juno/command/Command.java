package juno.command;

import juno.manager.exception.TaskManagerException;

/**
 * Abstract class for all possible commands.
 * Defines how commands can be executed.
 * Superclass for all other *Command classes in juno.command package
 */
public abstract class Command {

    /**
     * Runs the command. Subclasses must implement this method.
     * and define the specific behaviour of the command.
     *
     * @throws TaskManagerException If an error occurs during command execution.
     */
    public abstract String runCommand() throws TaskManagerException;

    /**
     * Determines if the command being executed changes the while loop behaviour in Juno class.
     * Overriden by ExitCommand class, where it returns false instead.
     *
     * @return True if the command is considered to be in a while loop, otherwise false.
     */
    public boolean isInWhileLoop() {
        return true;
    }
}
