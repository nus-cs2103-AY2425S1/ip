package dudu.command;

import java.io.IOException;

import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents a help user command into the chatbot
 */
public class CommandHelp extends Command {
    /**
     * Executes the exit command by displaying a goodbye message to the user.
     *
     * @param taskList The task list (not used in this command).
     * @param ui The user interface to display the goodbye message.
     * @param storage The storage (not used in this command).
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        return ui.getHelpMessage();
    }

    /**
     * Indicates that this command will not cause the application to exit.
     *
     * @return false, as this command always does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
