package muller.command;

import muller.storage.Storage;
import muller.task.TaskList;
import muller.ui.Ui;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}
