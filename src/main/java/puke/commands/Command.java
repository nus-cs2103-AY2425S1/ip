package puke.commands;

import puke.TaskList;
import puke.exceptions.PukeException;
import puke.message.MessageBuilder;

/**
 * Abstract base class for all commands in the application.
 * Defines the common interface for executing commands.
 */
public abstract class Command {

    /**
     * Executes the command using the provided TaskList and MessageBuilder.
     *
     * @param taskList the TaskList on which the command operates.
     * @param messageBuilder the MessageBuilder used to construct and send messages.
     * @return the result message after executing the command.
     * @throws PukeException if an error occurs during command execution.
     */
    public abstract String execute(TaskList taskList, MessageBuilder messageBuilder) throws PukeException;
}
