package susan.command;

import susan.ui.Storage;
import susan.task.TaskList;
import susan.ui.Ui;

/**
 * Represents a command to end session with user.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }
}