package command;

import java.util.ArrayList;
import java.util.List;

import tasklist.TaskList;
import tasks.Task;
import ui.CommandLineUi;

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
     * @param ui       The CommandLineUI used to interact with the user.
     */
    public void execute(TaskList tasklist, CommandLineUi ui) {
        List<Task> matches = new ArrayList<Task>();

        List<Task> tasks = tasklist.getTasks();

        for (Task t : tasks) {
            // Add task if matches keyward
            if (t.toString().indexOf(keyword) != -1) {
                matches.add(t);
            }
        }

        // Show matches
        for (int i = 0; i < matches.size(); i++) {
            ui.speakLine((i + 1) + ". " + matches.get(i).toString());
        }

    }

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return false, as this command does not cause the application to exit.
     */
    public boolean isExit() {
        return false;
    }
}
