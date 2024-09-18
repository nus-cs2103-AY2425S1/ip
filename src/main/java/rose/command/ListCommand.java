package rose.command;

import rose.Storage;
import rose.TaskList;
import rose.Ui;

/**
 * Represents a command used by user to show the list of tasks.
 */
public class ListCommand extends Command {

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.showTasks(ui);
    }
}
