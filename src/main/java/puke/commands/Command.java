package puke.commands;

import puke.exceptions.PukeException;
import puke.TaskList;
import puke.message.MessageBuilder;

/**
 * Abstract base class for all commands in the application.
 * Defines the common interface for executing a command.
 */
public abstract class Command {

    /**
     * Executes the command using the provided TaskList and MessageBuilder.
     *
     * @param taskList the TaskList to perform operations on
     * @param messageBuilder the MessageBuilder to construct and send messages
     * @throws PukeException if an error occurs during command execution
     */
    public abstract void execute(TaskList taskList, MessageBuilder messageBuilder) throws PukeException;
}
