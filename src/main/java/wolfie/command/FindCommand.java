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

    /**
     * Constructs a new FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        assert keyword != null && !keyword.isEmpty() : "Keyword should not be null or empty.";
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, WolfieException {
        assert tasks != null : "TaskList should not be null.";
        List<Task> matchingTasks = tasks.getTasks().stream()
                .filter(task -> task.getDescription().contains(keyword))
                .toList();
        //System.out.println(matchingTasks);
        if (matchingTasks.isEmpty()) {
            throw new WolfieException("No tasks found matching the keyword.");
        } else {
            return ui.showMatchingTasks(matchingTasks);
        }
    }
}
