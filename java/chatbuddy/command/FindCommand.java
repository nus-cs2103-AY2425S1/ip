package chatbuddy.command;

import chatbuddy.storage.Storage;
import chatbuddy.task.Task;
import chatbuddy.task.TaskList;
import chatbuddy.ui.Ui;

import java.util.ArrayList;

/**
 * Represents the command to find tasks with a specific keyword in the task list.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a new FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for in the task descriptions.
     */
    public FindCommand(String keyword) {
        assert keyword != null && !keyword.trim().isEmpty() : "Keyword must not be null or empty";
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks that contain the keyword.
     * Displays the matching tasks.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui instance to display results to the user.
     * @param storage The Storage instance to save tasks (not used in this command).
     * @return The output to display to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = getMatchingTasks(tasks);

        if (matchingTasks.isEmpty()) {
            ui.showError("No matching tasks found with the keyword: " + keyword);
        } else {
            ui.showFindResult(matchingTasks);
        }

        return ui.getOutput();
    }

    /**
     * Finds and returns a list of tasks matching the keyword.
     *
     * @param tasks The TaskList to search through.
     * @return A list of matching tasks.
     */
    private ArrayList<Task> getMatchingTasks(TaskList tasks) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
