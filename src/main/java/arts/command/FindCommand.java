package arts.command;

import arts.ArtsException;
import arts.task.TaskList;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand implements Command {
    private final TaskList tasks;
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified task list and keyword.
     *
     * @param tasks   The list of tasks to search within.
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(TaskList tasks, String keyword) {
        this.tasks = tasks;
        this.keyword = keyword;
    }

    /**
     * Executes the find command, searching for tasks that contain the keyword
     * and returning them as a string.
     *
     * @throws ArtsException If an error occurs during execution.
     */
    @Override
    public String execute() throws ArtsException {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            String task = tasks.getTask(i).toString();
            if (task.contains(keyword)) {
                sb.append(++count).append(". ").append(task).append("\n");
            }
        }
        if (count == 0) {
            return "No matching tasks found.";
        } else {
            return "Here are the matching tasks in your list:\n" + sb.toString();
        }
    }
}
