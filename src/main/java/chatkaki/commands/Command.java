package chatkaki.commands;

import java.io.IOException;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @throws IOException if an I/O error occurs during execution.
     */
    public abstract void execute() throws IOException;

    /**
     * Indicates whether this command is an exit command.
     *
     * @return true if this is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
