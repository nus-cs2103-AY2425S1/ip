package friday.command;

import friday.util.Storage;
import friday.util.TaskList;
import friday.util.Ui;

/**
 * Represents a command to find tasks that contain a specified keyword in their description.
 * Inherits from the Command class and provides functionality to search for matching tasks.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in the task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.filterTasks(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()));
        return ui.showMatchingTasks(matchingTasks);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
