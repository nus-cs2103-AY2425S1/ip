package astra.command;

import astra.Storage;
import astra.TaskList;
import astra.Ui;

/**
 * Represents a command to sort the list of tasks.
 */
public class SortCommand extends Command {
    public SortCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList sortedTasks = tasks.sortByDeadline();
        String msg = "Here are the tasks in your list sorted by deadline:\n" + sortedTasks.toString();
        ui.display(msg);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        TaskList sortedTasks = tasks.sortByDeadline();
        return "Here are the tasks in your list sorted by deadline:\n" + sortedTasks.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
