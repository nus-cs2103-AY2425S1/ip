package susan.command;

import susan.ui.Storage;
import susan.task.TaskList;
import susan.ui.Ui;

/**
 * Represents a command to greet the user.
 */
public class HelloCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showWelcome();
    }
}