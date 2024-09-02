package rose.command;

import rose.Storage;
import rose.TaskList;
import rose.Ui;

/**
 * Represents a command used by user to show the list of tasks.
 */
public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.showTasks(ui);
    }
}
