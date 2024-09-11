package edith.command;

import edith.Ui;
import edith.Storage;
import edith.EdithException;
import edith.task.TaskList;

/**
 * Represents a command to exit the application.
 * The ExitCommand class is used to signal the end of the user's session with the chatbot.
 */
public class ExitCommand extends Command {
    /**
     * Executes the ExitCommand by displaying the exit message to the user.
     *
     * <p>This method will:
     * <ul>
     *     <li>Display a farewell message to the user through the Ui.</li>
     * </ul>
     *
     * @param tasks The TaskList associated with the current session (not used in this command).
     * @param ui The Ui used to display the exit message to the user.
     * @param storage The Storage used to save any changes (not used in this command).
     * @throws EdithException This method does not throw any EdithException.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EdithException {
        return ui.showExitMessage();
    }

    /**
     * Returns whether this command is an exit command.
     *
     * @return true because this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
