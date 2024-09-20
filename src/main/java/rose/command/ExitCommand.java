package rose.command;

import rose.Storage;
import rose.TaskList;
import rose.Ui;

/**
 * Represents a command used by user to close the chatbot.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command to close the chatbot.
     *
     * @param tasks The TaskList, not used in this method.
     * @param ui The Ui instance for displaying the closing message to the user.
     * @param storage The Storage instance, not used in this method.
     * @return A message indicating that the chatbot is closing.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showClosing();
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return true, as this command is used to exit the chatbot.
     */
    public boolean isExit() {
        return true;
    }
}
