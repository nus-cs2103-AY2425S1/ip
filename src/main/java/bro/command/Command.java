package bro.command;

import bro.BroException;
import bro.ui.UI;

/**
 * Represents a command in the Bro application.
 * Each command is executed with a specific user interface and can indicate
 * whether the application should exit after execution.
 */
public interface Command {

    /**
     * Executes the command with the provided user interface.
     *
     * @param ui The user interface through which the command interacts.
     * @throws BroException If an error occurs during command execution.
     */
    String execute(UI ui) throws BroException;

    /**
     * Indicates whether the command will cause the application to exit.
     *
     * @return <code>true</code> if the command will cause the application to exit,
     *         <code>false</code> otherwise.
     */
    boolean isExit();
}
