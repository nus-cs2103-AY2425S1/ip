package noosy.commands;

import noosy.exception.NoosyException;
import noosy.storage.Storage;
import noosy.task.TaskList;
import noosy.ui.Ui;

/**
 * Abstract base class for all commands in the Noosy task management chatbot.
 * This class defines the common structure and behavior for all commands.
 */
public abstract class Command {

    /**
     * Executes the command.
     * This method should be implemented by all concrete command classes.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The user interface for displaying output.
     * @param storage The storage for persisting tasks.
     * @throws NoosyException If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws NoosyException;

    /**
     * Checks if this command is an exit command.
     * By default, commands are not exit commands.
     *
     * @return true if this is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
