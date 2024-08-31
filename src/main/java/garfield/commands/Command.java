package garfield.commands;

import garfield.exceptions.GarfieldException;
import garfield.storage.Storage;
import garfield.tasks.TaskList;
import garfield.ui.Ui;

/**
 * The Command class serves as an abstract base class for all user commands
 * in the Garfield chatbot application. Each specific command will extend
 * this class and implement its own behavior.
 */
public abstract class Command {

    /**
     * Constructs a new Command instance.
     * This constructor is typically invoked by subclasses.
     */
    public Command() {}

    /**
     * Returns whether this command should cause the application to exit.
     *
     * @return {@code true} if the application should exit after this command,
     *         {@code false} otherwise. The default implementation returns {@code false}.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command with the given TaskList, Ui, and Storage.
     * Subclasses should override this method to provide specific command behavior.
     *
     * @param taskList The TaskList that the command will operate on.
     * @param ui The Ui object used to interact with the user.
     * @param storage The Storage object used to save or load tasks.
     * @throws GarfieldException If an error occurs during command execution.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws GarfieldException {
    }
}
