package chatsy.commands;

import chatsy.TaskManager;
import chatsy.exceptions.InvalidTaskStringException;

/**
 * Handles the "find" command which searches for tasks by a given keyword.
 * It retrieves and displays a list of tasks that match the specified keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a FindCommand with the given keyword to search for tasks.
     *
     * @param keyword The keyword used to find matching tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks containing the keyword.
     *
     * @param taskManager The task manager where tasks are searched.
     * @return A string response listing the tasks that match the keyword.
     * @throws InvalidTaskStringException If the keyword is invalid or empty.
     */
    @Override
    public String execute(TaskManager taskManager) throws InvalidTaskStringException {
        String result = taskManager.findTasks(keyword);
        if (result.isEmpty()) {
            return "No matching tasks found.";
        }
        return "Here are the matching tasks in your list:\n" + result;
    }
}
