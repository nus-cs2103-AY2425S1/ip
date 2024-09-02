package yoda.commands;

import yoda.exceptions.YodaException;

/**
 * An abstract class that represents a chatbot command.
 */
public abstract class Command {

    /**
     * Executes the command. Specific implementation provided by subclasses.
     * @throws YodaException if input was invalid.
     */
    public abstract void run() throws YodaException;
}
