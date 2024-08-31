package command;

import java.util.ArrayList;
import java.util.List;

import tasklist.TaskList;
import tasks.Task;

/**
 * Represents a command to find tasks in the task list that match a given keyword.
 */
public class FindCommand extends Command {
    /** The keyword to search for in the task descriptions. */
    protected String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in the task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;

    }

    /**
     * Executes the command by searching the task list for tasks that contain the specified keyword
     * and displaying the matching tasks in the command line interface.
     *
     * @param tasklist The TaskList to search for matching tasks.
     * @return List of tasks.
     */
    @Override
    public String execute(TaskList tasklist) {
        List<Task> matches = new ArrayList<Task>();

        List<Task> tasks = tasklist.getTasks();

        for (Task t : tasks) {
            // Add task if matches keyward
            if (t.toString().indexOf(keyword) != -1) {
                matches.add(t);
            }
        }

        // Show matches
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matches.size(); i++) {
            sb.append((i + 1) + ". " + matches.get(i).toString() + "\n");
        }

        return sb.toString();

    }

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return false, as this command does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
