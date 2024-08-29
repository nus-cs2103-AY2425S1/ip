package silverwolf.command;

import silverwolf.storage.Storage;
import silverwolf.task.Task;
import silverwolf.task.TaskList;
import silverwolf.ui.Ui;

import java.util.List;

/**
 * The FindCommand class represents a command to find a task by searching for a keyword.
 * It extends the abstract Command class and implements the logic to list all tasks.
 */
public class FindCommand extends Command{
    private final String keyword;
    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command to search for tasks that contain the specified keyword.
     *
     * This method searches through the task list for tasks whose descriptions include
     * the given keyword and displays the matching tasks to the user using the UI.
     *
     * @param tasks The {@link TaskList} containing the tasks to be searched.
     * @param ui The {@link Ui} component used to display the results to the user.
     * @param storage The {@link Storage} component, which is not used in this method but is included
     *                in the method signature for consistency with other command implementations.
     *
     * @see TaskList#findTasks(String)
     * @see Ui#showMatchingTasks(List)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showMatchingTasks(matchingTasks);
    }
}
