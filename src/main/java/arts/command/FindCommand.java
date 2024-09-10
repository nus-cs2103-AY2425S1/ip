package arts.command;

import arts.ArtsException;
import arts.task.Task;
import arts.task.TaskList;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand implements Command {
    private static final String NO_MATCHING_TASKS_MESSAGE = "No matching tasks found.";
    private static final String MATCHING_TASKS_HEADER = "Here are the matching tasks in your list:\n";

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

        for (Task task : tasks.getTasks()) {
            String taskString = task.toString();
            if (taskString.contains(keyword)) {
                sb.append(++count).append(". ").append(taskString).append("\n");
            }
        }

        return count == 0 ? NO_MATCHING_TASKS_MESSAGE : MATCHING_TASKS_HEADER + sb.toString();
    }
}
