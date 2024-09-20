package duck.command;

import duck.storage.Storage;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents a command to remind the user about upcoming deadlines.
 */
public class RemindCommand implements Command {


    /**
     * Executes the command to be reminded of upcoming deadlines.
     * Updates the UI.
     *
     * @param list The task list on which the action is performed.
     * @param ui The UI to display information.
     * @param storage The storage for tasks.
     */
    @Override
    public void executeCommand(TaskList list, Ui ui, Storage storage) {
        TaskList upcomingDeadlines = list.getUpcomingDeadlines();
        ui.showUpcomingDeadlines(upcomingDeadlines);
    }
}
