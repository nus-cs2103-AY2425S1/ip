package duck.command;

import duck.storage.Storage;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents a command to remind the user about upcoming deadlines.
 */
public class RemindCommand implements Command {

    @Override
    public void executeCommand(TaskList list, Ui ui, Storage storage) {
        TaskList upcomingDeadlines = list.getUpcomingDeadlines();
        ui.showUpcomingDeadlines(upcomingDeadlines);
    }
}
