package Gary.command;

import Gary.Storage;
import Gary.TaskList;
import Gary.Ui;

/**
 * The {@code Command} class represents an abstract command in the task management application.
 * Subclasses should provide specific implementations for executing the command.
 */
public abstract class Command {

    /**
     * Executes the command with the given {@code TaskList}, {@code Ui}, and {@code Storage}.
     * Subclasses should override this method to provide specific command behavior.
     *
     * @param taskList The {@code TaskList} object containing tasks to be manipulated.
     * @param ui The {@code Ui} object for user interaction.
     * @param storage The {@code Storage} object for saving and loading tasks.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Indicates whether this command is a "bye" command, which ends the application.
     * Subclasses should override this method to specify if the command is a "bye" command.
     *
     * @return {@code true} if this is a "bye" command, {@code false} otherwise.
     */
    public abstract boolean isBye();
}
