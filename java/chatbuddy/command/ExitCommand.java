package chatbuddy.command;

import chatbuddy.storage.Storage;
import chatbuddy.task.TaskList;
import chatbuddy.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to display the goodbye message and terminates the session.
     * This method is typically called when the user triggers the "bye" command to exit the chatbot.
     *
     * @param tasks   The task list (unused in this command).
     * @param ui      The user interface to display the goodbye message.
     * @param storage The storage (unused in this command).
     * @return The goodbye message to be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        return ui.getOutput();
    }

    /**
     * Returns true, indicating that this command will terminate the application.
     *
     * @return true as the command will exit the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
