package dudu.command;

import java.io.IOException;

import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents a command to help user with command syntax.
 */
public class HelpCommand extends Command {
    /**
     * Returns a help message.
     *
     * @param taskList Task list containing the tasks.
     * @param ui User interface to interact with the user.
     * @param storage Storage to save tasks.
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        return ui.getHelpMessage();
    }
}
