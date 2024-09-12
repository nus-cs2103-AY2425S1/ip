package friday.command;

import friday.task.TaskList;
import friday.util.Storage;
import friday.util.Ui;

/**
 * Represents a command to find tasks in the task list that contain a specified keyword.
 */
public class FindCommand extends Command {
    private final String find;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param find The array containing the keyword to search for.
     */
    public FindCommand(String[] find) {
        assert find != null : "Find command input array should not be null";
        assert find.length > 1 : "Find command input array should have at least 2 elements";
        this.find = find[1];
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
        return tasks.findTasks(find);
    }
}
