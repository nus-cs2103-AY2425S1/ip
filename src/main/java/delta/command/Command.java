package delta.command;

import delta.exception.DeltaException;
import delta.util.Storage;
import delta.util.TaskList;
import delta.util.Ui;

/**
 * Includes method to determine if current command is the exiting command,
 * and method to execute specific commands according to subclass.
 * Serves as the parent class for subclasses AddCommand, MarkCommand,
 * UnmarkCommand, DeleteCommand, PrintCommand and ExitCommand.
 */
public abstract class Command {
    public Command(CommandType type) {}

    /**
     * Determines if specific command is the exit command.
     *
     * @return True if exit command else False.
     */
    public abstract boolean isExit();

    /**
     * Executes specific commands for each subclass.
     *
     * @param tasks List of current tasks.
     * @param ui UI object to deal with printing executed message.
     * @param storage Storage object to save tasklist after each command.
     * @return Command executed message.
     * @throws DeltaException If error in executing specific commands.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DeltaException;
}
