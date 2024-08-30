package nether.command;

import nether.Ui;
import nether.storage.Storage;
import nether.task.TaskList;

/**
 * Represents a command to exit the application.
 * <p>
 * The {@code ExitCommand} class handles the termination of the application by printing an exit message
 * and signaling that the application should close.
 * </p>
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command by displaying an exit message to the user.
     * <p>
     * This method interacts with the user interface to print a goodbye message and does not modify the task list
     * or storage.
     * </p>
     *
     * @param tasks The {@code TaskList} instance (unused in this command).
     * @param ui The {@code Ui} instance used to interact with the user.
     * @param storage The {@code Storage} instance (unused in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
    }

    /**
     * Returns {@code true} to indicate that this command represents an exit command.
     *
     * @return {@code true} to indicate that the application should terminate.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}