package fishman.command;

import java.util.List;

import fishman.task.Task;
import fishman.task.TaskList;
import fishman.utils.Ui;


/**
 * Represents the command to find a task in the task list.
 * This command implements the Command interface and is for finding a task by searching for a keyword.
 */
public class FindCommand implements Command {
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
     *      Filters the task matched based on the keyword and displays matched tasks in the list.
     *
     * @param taskList The Task List in which the task is matched from.
     * @param ui The Ui instance used to generate the tasks.
     * @return The confirmation message indicating the command execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        List<Task> matchedTasks = taskList.getAllTasks().stream()
                .filter(task -> task.getTaskDescription().toLowerCase()
                .contains(keyword.toLowerCase()))
                .toList();

        return ui.getFindResultsMessage(matchedTasks);
    }
}
