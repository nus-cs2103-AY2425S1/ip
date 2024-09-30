package vinegar.command;

import vinegar.task.Task;
import vinegar.task.TaskList;
import vinegar.VinegarException;
import vinegar.storage.Storage;
import vinegar.ui.Ui;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the find command that searches tasks by a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String[] inputParts) throws VinegarException {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new VinegarException("Please specify a keyword to search.");
        }
        this.keyword = inputParts[1].trim().toLowerCase(); // Convert keyword to lowercase for case-insensitive search
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        List<Task> matchingTasks = tasks.getTasks().stream()
                .filter(task -> task.toString().toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        if (matchingTasks.isEmpty()) {
            return ui.showMessage("No tasks found containing the keyword: " + keyword);
        } else {
            return ui.showMatchingTaskList(matchingTasks);
        }
    }
}
