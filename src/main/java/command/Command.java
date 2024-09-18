package command;

import exceptions.BuddyException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The Command class serves as an abstract base class for all commands.
 * Each specific command should implement the abstract {@code execute} method.
 * Commands represent actions that can be performed on a task list, which may involve updating tasks,
 * displaying information, or modifying the state of the task list.
 */
public abstract class Command {

    /**
     * Executes the command, performing the necessary actions on the task list.
     * Each concrete command class must provide its implementation of this method.
     *
     * @param tasks   the task list on which the command operates.
     * @param ui      the user interface instance for displaying output messages.
     * @param storage the storage handler to save any changes made to the task list.
     * @return a message indicating the result of executing the command.
     * @throws BuddyException if there is an issue during execution, such as invalid input or task operation failures.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException;

    /**
     * Indicates whether the command should result in the termination of the program.
     * By default, this method returns {@code false} to indicate that the program should not exit.
     * Commands that intend to terminate the program should override this method to return {@code true}.
     *
     * @return {@code true} if the command should cause the program to exit, {@code false} otherwise.
     */
    public boolean isExit(){
        return false;
    }
}