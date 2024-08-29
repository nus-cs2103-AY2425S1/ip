package lict.command;

import lict.Storage;
import lict.TaskList;
import lict.Ui;
import lict.LictException;

/**
 * The abstract {@code Command} class represents a command that can be executed in the application.
 * Subclasses of {@code Command} must implement the {@code execute} method to define the specific behavior of the command.
 */
public abstract class Command {

    /**
     * Returns whether the command is an exit command.
     * By default, commands do not exit the application, so this method returns {@code false}.
     * Subclasses can override this method if they represent exit commands.
     *
     * @return {@code false} by default, indicating the application should not exit.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command, performing the necessary operations on the task list, UI, and storage.
     * This method must be implemented by subclasses to define specific command behavior.
     *
     * @param tasks   The task list on which the command operates.
     * @param ui      The UI component for displaying messages to the user.
     * @param storage The storage component for saving changes to the task list.
     * @throws LictException If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws LictException;
}
