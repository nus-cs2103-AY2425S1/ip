package shenhe.command;

import shenhe.Storage;
import shenhe.TaskList;
import shenhe.Ui;




/**
 * Represents a command to exit the application.
 * <p>
 * The {@code ExitCommand} class is responsible for handling the command that terminates the application.
 * When this command is executed, it will display a goodbye message and indicate that the application should exit.
 * </p>
 */
public final class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * <p>
     * This method displays a goodbye message to the user through the {@link Ui} instance.
     * </p>
     *
     * @param tasks The current task list.
     * @param ui The user interface instance used for displaying messages.
     * @param storage The storage instance used for saving tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    /**
     * Indicates whether this command should exit the application.
     * <p>
     * This method returns {@code true} to signal that the application should terminate.
     * </p>
     *
     * @return {@code true}, indicating that the application should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
