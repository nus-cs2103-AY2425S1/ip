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
        assert tasks != null : "TaskList cannot be null";
        assert keyword != null && !keyword.trim().isEmpty() : "Keyword cannot be null or empty";

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
        assert keyword != null && !keyword.trim().isEmpty() : "Keyword must be valid before execution";

        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            String taskString = task.toString();
            assert taskString != null : "Task string representation should not be null";

            if (taskString.contains(keyword)) {
                sb.append(++count).append(". ").append(taskString).append("\n");
            }
        }

        assert count >= 0 : "Count of matching tasks should not be negative";

        // Anime-like response
        if (count == 0) {
            return "Oh no! 😱 No tasks matched your search. Keep your spirits high, the right task will appear! 🌈";
        } else {
            return String.format("Eureka! 🎉 Here are the tasks that matched your quest for '%s':\n%s"
                            + "Keep up the great work, hero! 💪✨",
                    keyword, sb.toString());
        }
    }

}
