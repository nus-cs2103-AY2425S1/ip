package samson.command;

import samson.Storage;
import samson.Ui;
import samson.task.TaskList;

/**
 * The <code> Command </code> class is an abstract class that represents a command in the application.
 * Subclasses must implement the methods to execute the command and to determine if the command
 * should cause the application to exit.
 */
public abstract class Command {

    /**
     * Executes the command with the provided task list, UI, and storage.
     *
     * @param tasks   The list of tasks on which the command will operate.
     * @param ui      The UI object used to interact with the user.
     * @param storage The storage object used to save tasks to the file.
     * @throws Exception If an error occurs during the execution of the command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    /**
     * Determines whether the command should cause the application to exit.
     *
     * @return true if the command should cause the application to exit, {@code false} otherwise.
     */
    public abstract boolean isExit();
}

