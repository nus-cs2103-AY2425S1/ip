package nextgpt.command;

import nextgpt.TaskList;
import nextgpt.Storage;
import nextgpt.Ui;


/**
 * Subclass of Command that exits user.
 */
public class ExitCommand extends Command {
    /**
     * Notifies user with exit message.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.bye();
    }

    public boolean isExit() {
        return true;
    }
}
