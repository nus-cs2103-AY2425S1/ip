package joe.commands;

import joe.exceptions.InvalidCommandException;
import joe.exceptions.InvalidIndexException;

import java.lang.reflect.InvocationTargetException;

/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @throws IllegalArgumentException If the command is invalid.
     * @throws InvalidCommandException  If the command is invalid.
     */
    public abstract String execute() throws InvalidIndexException;

    /**
     * Returns true if the command is a bye command.
     *
     * @return True if the command is a bye command, false otherwise.
     */
    public boolean isBye() {
        return false;
    }
}
