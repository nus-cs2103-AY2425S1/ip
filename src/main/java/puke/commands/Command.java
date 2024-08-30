package puke.commands;

import puke.exceptions.PukeException;
import puke.tasklist.TaskManager;
import puke.ui.MessageBuilder;

/**
 * Abstract base class for all commands in the application.
 * Defines the common interface for executing a command.
 */
public abstract class Command {

    /**
     * Executes the command using the provided TaskManager and MessageBuilder.
     *
     * @param taskManager the TaskManager to perform operations on
     * @param messageBuilder the MessageBuilder to construct and send messages
     * @throws PukeException if an error occurs during command execution
     */
    public abstract void execute(TaskManager taskManager, MessageBuilder messageBuilder) throws PukeException;
}
