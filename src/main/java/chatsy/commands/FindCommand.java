package chatsy.commands;

import chatsy.TaskManager;
import chatsy.exceptions.InvalidTaskStringException;

/**
 * Handles the "find" command which searches for tasks by a keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskManager taskManager) throws InvalidTaskStringException {
        String result = taskManager.findTasks(keyword);
        if (result.isEmpty()) {
            return "No matching tasks found.";
        }
        return "Here are the matching tasks in your list:\n" + result;
    }
}
