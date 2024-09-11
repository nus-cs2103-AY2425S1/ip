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
        assert keyword != null && !keyword.isEmpty() : "Keyword should not be null or empty";
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        TaskList matchingTasks = tasks.findTasks(keyword);
        return ui.showMatchingTasks(matchingTasks);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
