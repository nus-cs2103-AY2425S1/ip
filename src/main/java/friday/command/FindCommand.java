package friday.command;

import friday.task.TaskList;
import friday.util.Storage;
import friday.util.Ui;

/**
 * Represents a command to find tasks in the task list that contain a specified keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param commandArgs The array containing the keyword to search for.
     */
    public FindCommand(String[] commandArgs) {
        this.keyword = extractKeyword(commandArgs);
    }

    private String extractKeyword(String[] commandArgs) {
        assert commandArgs != null : "Find command input array should not be null";
        assert commandArgs.length > 1 : "Find command input array should have at least 2 elements";
        return commandArgs.length > 1 ? commandArgs[1] : "";
    }

    /**
     * Executes the find command by searching for tasks that contain the specified keyword.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui object that handles user interactions.
     * @param storage The Storage object that handles data storage.
     * @return The string representation of tasks in the task list matching the keyword.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "Task list should not be null";
        assert ui != null : "UI should not be null";
        assert storage != null : "Storage should not be null";
        return tasks.findTasks(keyword);
    }
}
