package tudee.command;

import tudee.task.TaskList;
import tudee.ui.Ui;
import tudee.storage.Storage;

/**
 * Represents a command to stop chatting with the chatbot.
 * This command updates the user interface to indicate that the application is exiting.
 */
public class ByeCommand extends Command {
    /**
     * Executes the command to terminate the application.
     * Updates the user interface to show a farewell message.
     *
     * @param tasks The task list (not used in this command).
     * @param ui The user interface to update with the exit message.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    /**
     * Indicates that this command signals the end of the application.
     *
     * @return True, as this command will terminate the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
