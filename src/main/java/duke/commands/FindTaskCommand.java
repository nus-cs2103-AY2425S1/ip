package duke.commands;

import java.util.ArrayList;
import java.util.List;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to find tasks in the task list that match a specified keyword.
 * <p>
 * This command searches for tasks whose descriptions contain the given keyword
 * and displays the matching tasks to the user.
 * </p>
 */
public class FindTaskCommand extends Command {

    /** The keyword used to search for matching tasks in the task list. */
    private final String keyword;

    /**
     * Constructs a new {@code FindTaskCommand} with the specified keyword.
     *
     * @param keyword The keyword used to search for tasks in the task list.
     */
    public FindTaskCommand(String keyword) {
        // Assert that the keyword is not null or empty
        assert keyword != null : "Keyword must not be null";
        assert !keyword.trim().isEmpty() : "Keyword must not be empty";
        this.keyword = keyword;
    }

    /**
     * Executes the command by searching the task list for tasks that
     * contain the specified keyword in their descriptions.
     * <p>
     * The matching tasks are then displayed using the user interface.
     * </p>
     *
     * @param taskList The {@link TaskList} containing the list of tasks to be searched.
     * @param ui The {@link Ui} used to display the found tasks.
     * @param storage The {@link Storage} system responsible for saving and
     *                loading tasks (not used in this implementation).
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        // Assert that taskList and ui are not null
        assert taskList != null : "Task list must not be null";
        assert ui != null : "UI object must not be null";

        List<Task> tasks = taskList.getTasks();
        List<Task> foundTasks = new ArrayList<>();

        // Search for tasks that contain the keyword in their description
        for (Task task : tasks) {
            // Assert that the task description is not null
            assert task.getDescription() != null : "Task description must not be null";

            if (task.getDescription().contains(this.keyword)) {
                foundTasks.add(task);
            }
        }

        return ui.formatTaskListings(foundTasks, false);
    }
}
