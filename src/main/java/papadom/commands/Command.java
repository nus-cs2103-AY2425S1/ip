package papadom.commands;

import papadom.Storage.*;
import papadom.Ui;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface for outputting messages.
     * @param storage The storage system for saving the task list.
     * @throws Exception If an error occurs during execution.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws Exception;
}
