package Alex.Command;

import Alex.Exceptions.AlexException;
import Alex.Storage.Storage;
import Alex.Task.TaskList;
import Alex.Ui.Ui;

/**
 * Represents a command that can be executed in the application.
 */
public interface Command {
    /**
     * Executes the command.
     * @param tasks The TaskList to operate on.
     * @param ui The Ui to interact with.
     * @param storage The Storage to handle saving/loading tasks.
     * @throws AlexException If an error occurs during execution.
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws AlexException;

    /**
     * Checks if this command is an exit command.
     * @return True if the command is an exit command, otherwise false.
     */
    boolean isExit();
}
