package sammy.command;

import sammy.SammyException;
import sammy.Storage;
import sammy.task.TaskList;
import sammy.Ui;

import java.io.IOException;

/**
 * Represents an abstract command that can be executed.
 * Subclasses must implement the execute method to define specific behaviors.
 */
public abstract class Command {

    /**
     * Executes the command with the given TaskList, Ui, and Storage.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui The UI object to interact with the user.
     * @param storage The storage object to save or load data.
     * @throws SammyException If the command execution fails.
     * @throws IOException If an I/O error occurs.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws SammyException, IOException;

    /**
     * Determines if the command should exit the application.
     *
     * @return false by default, can be overridden by subclasses.
     */
    public boolean isExit() {
        return false;
    }
}

