package waterfall.command;

import java.io.IOException;
import java.util.List;

import waterfall.Storage;
import waterfall.Ui;
import waterfall.WaterfallException;
import waterfall.task.Task;
import waterfall.task.TaskList;

/**
 * Represents the <code>Command</code> object to search for specific string in the
 * task title.
 *
 * @author Wai Hong
 */
public class SearchCommand extends Command {
    final String targetTitle;

    /**
     * Constructs the command object to search for specific title in task list.
     *
     * @param targetTitle Target title string to be searched for.
     */
    public SearchCommand(String targetTitle) {
        this.targetTitle = targetTitle;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WaterfallException, IOException {
        List<Task> filteredTasks = tasks.searchTasks(targetTitle);
        return ui.showSearchedTaskListMessage(new TaskList(filteredTasks));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
