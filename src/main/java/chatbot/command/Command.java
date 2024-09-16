package chatbot.command;

import chatbot.exception.InputException;

/**
 * Represents the abstract concept of a Command
 * Has an abstract run() method to be implemented by concrete subclasses
 */
public abstract class Command {
    /**
     * Executes the command
     *
     * @return The String result of the command after it is run
     * @throws InputException A potential Input Exception
     */
    public abstract String run() throws InputException;
}
