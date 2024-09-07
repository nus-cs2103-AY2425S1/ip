package myapp.command;

import myapp.core.Storage;
import myapp.task.Task;
import myapp.task.TaskList;

/**
 * Represents a command that searches for tasks in the task list that match a given keyword.
 * The command returns a list of tasks that contain the keyword in their description.
 */
public class FindCommand extends Command {

    /** The keyword used to search for matching tasks. */
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword used to search for tasks.
     */
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    /**
     * Executes the command by searching the task list for tasks that contain the keyword in their description.
     * If matching tasks are found, they are returned in a formatted list. Otherwise, a message indicating
     * no matches is returned.
     *
     * @param tasks   The task list to search through.
     * @param storage The storage system (not used in this command).
     * @return A string message listing the matching tasks, or a message indicating no matches were found.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder list = new StringBuilder("Here are your matching tasks in your list:\n");
        boolean hasTasks = findMatchingTasks(tasks, list);

        return generateResultMessage(hasTasks, list);
    }

    /**
     * Returns false since a FindCommand does not terminate the application.
     *
     * @return false, indicating that this command does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Searches through the task list for tasks that contain the keyword in their description.
     * If matching tasks are found, they are appended to the list.
     *
     * @param tasks The task list to search through.
     * @param list The {@link StringBuilder} used to append the matching tasks.
     * @return true if matching tasks are found, false otherwise.
     */
    private boolean findMatchingTasks(TaskList tasks, StringBuilder list) {
        boolean hasTasks = false;
        int count = 1;

        for (Task task : tasks) {
            if (isTaskMatching(task)) {
                appendTaskToList(list, task, count);
                hasTasks = true;
                count++;
            }
        }

        return hasTasks;
    }

    /**
     * Checks if a task contains the keyword in its description.
     *
     * @param task The task to check.
     * @return true if the task contains the keyword, false otherwise.
     */
    private boolean isTaskMatching(Task task) {
        return task.getDescription().contains(keyword);
    }

    /**
     * Appends a task to the list with the appropriate numbering.
     *
     * @param list The {@link StringBuilder} to append the task to.
     * @param task The task to append.
     * @param count The number representing the task's position in the list.
     */
    private void appendTaskToList(StringBuilder list, Task task, int count) {
        list.append(count).append(". ").append(task).append("\n");
    }

    /**
     * Generates the result message based on whether matching tasks were found.
     *
     * @param hasTasks true if matching tasks were found, false otherwise.
     * @param list The list of tasks found.
     * @return The result message to be displayed to the user.
     */
    private String generateResultMessage(boolean hasTasks, StringBuilder list) {
        if (!hasTasks) {
            return "No tasks found that match keyword '" + keyword + "'.";
        } else {
            return list.toString();
        }
    }
}
