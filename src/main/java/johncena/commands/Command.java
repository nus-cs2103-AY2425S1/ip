package johncena.commands;

import johncena.exceptions.CenaException;


/**
 * The {@code Command} interface provides a method to execute a command.
 * Implementing classes should provide the specific implementation of the command.
 */
public interface Command {

    /**
     * Executes the command.
     *
     * @throws CenaException if an error occurs during the execution of the command
     */
    String execute() throws CenaException;
}
