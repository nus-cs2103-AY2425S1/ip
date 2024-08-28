package fishman.command;

import fishman.task.Task;
import fishman.task.TaskList;
import fishman.utils.Ui;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the command to find a task in the task list.
 * This command implements the Command interface and is for finding a task by searching for a keyword.
 */
public class FindCommand implements Command{
    /** The keyword of the task in the task list to be found. */
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

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
