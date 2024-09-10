package rose.command;

import rose.Storage;
import rose.TaskList;
import rose.Ui;

/**
 * Represents a command used by user to close the chatbot.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showClosing();
    }

    public boolean isExit() {
        return true;
    }
}
