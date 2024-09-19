package commands;

import bob.ExecutionStack;
import bob.Storage;
import bob.TaskList;
import bob.Ui;

/**
 * Abstract class representing a command that can be executed.
 * Concrete implementations of this class will define specific commands
 * and how they interact with the task list, user interface, and storage.
 */
public abstract class Command {
    /**
     * Executes the command using the provided task list, user interface, and storage.
     *
     * @param tasks the {@code TaskList} to be modified by the command.
     * @param ui the {@code Ui} to interact with the user.
     * @param storage the {@code Storage} to save or load tasks.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage, ExecutionStack execStack);

    public abstract String undo(TaskList tasks, Ui ui, Storage storage);

    /**
     * Determines if the command is an exit command.
     * The default implementation returns {@code false}.
     * Subclasses representing exit commands should override this method to return {@code true}.
     *
     * @return {@code true} if the command is an exit command; {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
