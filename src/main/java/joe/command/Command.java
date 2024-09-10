package joe.command;

import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.TaskList;

/**
 * The {@code Command} class is an abstract class that represents a user command
 * to be executed in the application. Subclasses must provide an implementation
 * for the {@code execute} method to define the specific behavior of the command.
 */
public abstract class Command {

    /**
     * Executes the command using the provided {@code TaskList}, {@code Ui}, and {@code Storage}.
     * Subclasses should implement this method to perform the specific action of the command.
     *
     * @param taskList The {@code TaskList} containing the current list of tasks.
     * @param ui The {@code Ui} object for interacting with the user.
     * @param storage The {@code Storage} object for handling file operations.
     * @throws JoeException If an error occurs during command execution.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException;

    /**
     * Indicates whether the command is an exit command that terminates the application.
     * By default, this returns {@code false}.
     *
     * @return {@code true} if the command is an exit command, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }

}

