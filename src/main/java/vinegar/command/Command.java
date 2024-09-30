package vinegar.command;

import vinegar.task.TaskList;
import vinegar.VinegarException;
import vinegar.storage.Storage;
import vinegar.ui.Ui;

import java.io.IOException;

/**
 * Abstract class representing a command.
 * <p>
 * Commands perform actions on the task list, such as adding, marking, unmarking, deleting tasks,
 * and interacting with the user interface (Ui) and storage. Subclasses must implement the
 * {@code execute} method to define specific behavior for each command.
 */
public abstract class Command {

    /**
     * Executes the command, performing the associated action on the task list, UI, and storage.
     *
     * @param tasks   The task list to modify or query.
     * @param ui      The UI for user interaction and feedback.
     * @param storage The storage for saving or loading tasks.
     * @throws VinegarException If the command encounters an error during execution.
     * @throws IOException      If an error occurs while interacting with the storage.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws VinegarException, IOException;

    /**
     * Determines whether the command signals an exit from the application.
     *
     * @return False by default. Override in subclasses like ExitCommand to return true.
     */
    public boolean isExit() {
        return false;
    }
}
