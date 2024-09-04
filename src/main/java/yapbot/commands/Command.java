package yapbot.commands;

import yapbot.exceptions.YapBotException;
import yapbot.util.Storage;
import yapbot.util.TaskList;
import yapbot.util.Ui;

/**
 * Abstract parent class that all commands must inherit from.
 */
public abstract class Command {

    /**
     *
     * @param tasks TaskList to be called for any task-related functions.
     * @param ui ui to be called for any printing of messages to the user.
     * @param storage storage to be called for any file interactions.
     * @return true if the command was executed successfully and false otherwise.
     */
    public abstract boolean execute(TaskList tasks, Ui ui, Storage storage) throws YapBotException;

    /**
     * Returns whether YapBot should terminate for this Command.
     *
     * @return true if the command is a terminating command and false otherwise.
     */
    public abstract boolean isExit();
}
