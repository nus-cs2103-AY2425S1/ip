package wolfie.command;

import java.io.IOException;
import java.util.List;

import wolfie.exception.WolfieException;
import wolfie.task.Task;
import wolfie.task.TaskList;
import wolfie.util.Storage;
import wolfie.util.Ui;

/**
 * Represents a command to find tasks by a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, WolfieException {
        List<Task> matchingTasks = tasks.getTasks().stream()
                .filter(task -> task.getDescription().contains(keyword))
                .toList();

        if (matchingTasks.isEmpty()) {
            throw new WolfieException("No tasks found matching the keyword.");
        } else {
            return ui.showMatchingTasks(matchingTasks);
        }
    }
}
