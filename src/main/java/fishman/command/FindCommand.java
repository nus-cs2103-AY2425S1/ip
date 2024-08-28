package fishman.command;

import fishman.task.Task;
import fishman.task.TaskList;
import fishman.utils.Ui;

import java.util.List;

/**
 * Represents the command to find a task in the task list.
 * This command implements the Command interface and is for finding a task by searching for a keyword.
 */
public class FindCommand implements Command{
    /** The keyword of the task in the task list to be found. */
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword to be matched.
     *
     * @param keyword The keyword to be matched to the tasks in the task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @inheritDoc
     *
     * Filters the task matched based on the keyword and displays matched tasks in the list.
     *
     * @param tasks The Task List in which the task is matched from.
     * @param ui The Ui object used to display the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        List<Task> matchedTask = tasks.getTasks().stream()
                .filter(task -> task.getDescription().toLowerCase()
                .contains(keyword.toLowerCase()))
                .toList();

        if (matchedTask.isEmpty()) {
            ui.displayEmptyFindResults();
        } else {
            ui.displayFindResults(matchedTask);
        }
    }
}
