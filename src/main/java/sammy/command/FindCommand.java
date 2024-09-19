package sammy.command;

import sammy.Storage;
import sammy.task.TaskList;
import sammy.Ui;
import sammy.task.Task;
import java.util.List;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
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

