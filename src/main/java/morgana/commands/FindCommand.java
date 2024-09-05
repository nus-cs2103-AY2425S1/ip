package morgana.commands;

import morgana.exceptions.MorganaException;
import morgana.storage.Storage;
import morgana.task.Task;
import morgana.task.TaskList;

/**
 * Represents a command to find tasks that match a given keyword in their description.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the specified keyword.
     *
     * @param keyword The keyword to search for in the task description.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws MorganaException {
        if (keyword.isEmpty()) {
            throw new MorganaException("Please enter a keyword to search for.");
        }
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                sb.append("%d. %s\n".formatted(i + 1, task));
            }
        }
        return sb.toString();
    }
}
