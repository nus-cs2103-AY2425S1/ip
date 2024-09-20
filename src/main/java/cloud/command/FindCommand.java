package cloud.command;

import cloud.util.Storage;
import cloud.util.TaskList;
import cloud.util.Ui;

/**
 * Represents a command to find tasks that match the specified keywords.
 * A <code>FindCommand</code> object does a search operation
 * within the task list.
 */
public class FindCommand extends Command {
    private final String keywords;

    public FindCommand(String keywords) {
        this.keywords = keywords;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = tasks.find(keywords);
        if (filteredTasks.getTaskCount() == 0) {
            return ui.showMatching("");
        }
        return ui.showMatching(filteredTasks.toString());
    }
}
