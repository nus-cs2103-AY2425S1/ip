package silverwolf.command;

import silverwolf.exception.SilverWolfException;
import silverwolf.storage.Storage;
import silverwolf.task.TaskList;
import silverwolf.ui.Ui;

/**
 * The Command class serves as an abstract base class for all command types in the application.
 * Each command represents an action that the user can perform, such as adding a task, deleting a
 * task, marking a task as done, etc.
 * Subclasses must implement the abstract execute method, which defines the specific behavior of the command.
 */
public abstract class Command {

    /**
     * Executes the command. This method must be implemented by all subclasses to define
     * the specific actions that the command will perform.
     *
     * @param tasks   The TaskList object that manages the list of tasks.
     * @param ui      The Ui object that handles user interaction.
     * @param storage The Storage object responsible for saving and loading tasks from the file.
     * @throws SilverWolfException If there is an error during the execution of the command (e.g., invalid input).
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SilverWolfException;

    /**
     * Indicates whether the command causes the application to exit.
     * By default, this returns false, meaning the application will continue running after the command is executed.
     * Subclasses can override this method if a command should cause the application to exit (e.g., "bye" command).
     *
     * @return false by default, indicating that the application should not exit after this command.
     */
    public boolean isExit() {
        return false;
    }
}
