package bob.command;

import bob.exception.BobException;
import bob.storage.Storage;
import bob.tasks.Task;
import bob.ui.Ui;

import java.util.ArrayList;

/**
 * Represents an abstract command that can be executed within the Bob application.
 * All specific command classes should extend this class and implement the execute method
 * to define their specific behavior.
 */
public abstract class Command {

    /**
     * Executes the command with the provided task list, storage, and user interface.
     * This method must be implemented by all subclasses to define the specific action
     * of the command.
     *
     * @param tasks   the list of tasks managed by Bob.
     * @param storage the storage handler for reading and writing tasks to the file.
     * @param ui      the user interface handler for displaying messages to the user.
     * @return a string message resulting from the command execution, typically for user feedback.
     * @throws BobException if there is an error during the execution of the command.
     */
    public abstract String execute(ArrayList<Task> tasks, Storage storage, Ui ui) throws BobException;
}

