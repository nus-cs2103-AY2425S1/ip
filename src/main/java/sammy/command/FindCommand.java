package sammy.command;

import sammy.Storage;
import sammy.task.TaskList;
import sammy.Ui;
import sammy.task.Task;
import java.util.List;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        assert keyword != null && !keyword.isEmpty() : "Keyword cannot be null or empty";
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "UI object cannot be null";
        assert storage != null : "Storage object cannot be null";

        List<Task> matchingTasks = tasks.getAllTasks().stream()
                .filter(task -> task.getDescription().contains(keyword))
                .toList();

        if (matchingTasks.isEmpty()) {
            return ui.showErrorMessage("No matching tasks found.");
        } else {
            return ui.showFindResults(tasks);
        }
    }
}

