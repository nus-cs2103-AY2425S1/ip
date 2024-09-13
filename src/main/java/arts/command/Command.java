package arts.command;

import arts.ArtsException;

/**
 * Represents a command that can be executed, potentially throwing an ArtsException.
 * This interface is intended to be implemented by classes that encapsulate specific
 * commands or actions within the application.
 */
public interface Command {

    /**
     * Executes the command. Implementations of this method should define the specific
     * actions that occur when the command is run. This method may throw an ArtsException
     * if the command cannot be executed successfully.
     *
     * @throws ArtsException If an error occurs during the execution of the command.
     */
    String execute() throws ArtsException;
}

