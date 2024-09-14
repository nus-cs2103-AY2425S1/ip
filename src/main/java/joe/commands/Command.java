package joe.commands;

import joe.exceptions.InvalidCommandException;

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
    public abstract String execute() throws IllegalArgumentException, InvalidCommandException;

    /**
     * Returns true if the command is a bye command.
     *
     * @return True if the command is a bye command, false otherwise.
     */
    public boolean isBye() {
        return false;
    }
}
