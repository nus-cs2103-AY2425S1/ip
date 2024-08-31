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
        boolean hasTasks = false;
        int count = 1;

        for (Task task : tasks) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                list.append(count).append(". ").append(task).append("\n");
                hasTasks = true;
                count++;
            }
        }

        if (!hasTasks) {
            return "No tasks found that match keyword '" + keyword + "'.";
        } else {
            return list.toString();
        }
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
}
